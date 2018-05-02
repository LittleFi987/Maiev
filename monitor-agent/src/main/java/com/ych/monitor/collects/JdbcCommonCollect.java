package com.ych.monitor.collects;

import com.ych.monitor.AbstractCollectors;
import com.ych.monitor.AgentLoader;
import com.ych.monitor.Collect;
import com.ych.monitor.bean.JdbcStatistics;
import com.ych.monitor.bean.Statistics;
import com.ych.monitor.collects.api.TransformMaker;
import com.ych.monitor.collects.core.JdbcCommonMaker;
import javassist.CtClass;
import javassist.CtMethod;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * @Author yechenhao
 * @Date 25/04/2018
 */
public class JdbcCommonCollect extends AbstractCollectors implements Collect {

    public static final String JDBC_NAME = "com.mysql.jdbc.NonRegisteringDriver";

    public final static JdbcCommonCollect INSTANCE = new JdbcCommonCollect();

    public static final String[] CONNECTION_AGENT_METHODS = new String[]{"prepareStatement"};
    public static final String[] PREPARED_STATEMENT_METHODS = new String[]{"executeQuery", "execute", "executeUpdate"};

    @Override
    public boolean isTarget(String className, ClassLoader classLoader, CtClass ctClass) {
        if (JDBC_NAME.equals(className)) {
            return true;
        }
        return false;
    }

    @Override
    public byte[] transform(ClassLoader loader, String className, byte[] classfileBuffer, CtClass ctClass) throws Exception {
        AgentLoader agentLoader = new AgentLoader(className, loader, ctClass);
        CtMethod connectMethod = ctClass.getMethod("connect", "(Ljava/lang/String;Ljava/util/Properties;)Ljava/sql/Connection;");
        AgentLoader.MthodSrcBuild build = new AgentLoader.MthodSrcBuild();
        TransformMaker maker = new JdbcCommonMaker();
        build.setBeginSrc(maker.begin());
        build.setErrorSrc(maker.error());
        build.setEndSrc(maker.end());
        agentLoader.updateMethod(connectMethod, build);
        return agentLoader.toBytecode();
    }

    @Override
    public Statistics begin(String className, String method) {
        JdbcStatistics jdbcStat = new JdbcStatistics(super.begin(className, method));
        jdbcStat.setLogType("sql");
        return jdbcStat;
    }

    @Override
    public void end(Statistics statistics) {
        super.end(statistics);
    }

    @Override
    public void error(Statistics statistics, Throwable throwable) {
        super.error(statistics, throwable);
    }

    @Override
    public void sendStatistics(Statistics statistics) {

    }

    @Override
    protected void sendStatisticByHttp(Statistics statistics, String type) {
        super.sendStatisticByHttp(statistics, type);
    }


    public class ConnectionHandler implements InvocationHandler {

        private final Connection connection;

        private ConnectionHandler(Connection connection) {
            this.connection = connection;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            boolean isTargetMethod = false;
            for (String connectionAgentMethod : CONNECTION_AGENT_METHODS) {
                if (connectionAgentMethod.equals(method.getName())) {
                    isTargetMethod = true;
                }
            }
            Object result = null;
            JdbcStatistics statistics = null;
            try {
                if (isTargetMethod) {
                    statistics = (JdbcStatistics) JdbcCommonCollect.this.begin(null, null);
                    statistics.setJdbcUrl(connection.getMetaData().getURL());
                    statistics.setSql((String)args[0]);
                }
                result = method.invoke(connection, args);
                // 代理PreparedStatement
                if (isTargetMethod && result instanceof PreparedStatement) {
                    PreparedStatement ps = (PreparedStatement)result;
                    result = proxyPreparedStatement(ps, statistics);
                }
            } catch (Exception e) {
                if (!(result instanceof PreparedStatement)) {
                    JdbcCommonCollect.this.error(statistics, e);
                }
            } finally {
                if (!(result instanceof PreparedStatement)) {
                    JdbcCommonCollect.this.end(statistics);
                }
            }
            return result;
        }

    }

    public PreparedStatement proxyPreparedStatement(PreparedStatement ps, JdbcStatistics statistics) {
        Object o = Proxy.newProxyInstance(JdbcCommonCollect.class.getClassLoader(), new Class[]{PreparedStatement.class}, new PreparedStatementHandler(ps, statistics));
        return (PreparedStatement)o;
    }

    /**
     * 代理connection
     *
     * @param connection
     * @return
     */
    public Connection proxyConnection(final Connection connection) {
        Object o = Proxy.newProxyInstance(JdbcCommonCollect.class.getClassLoader(), new Class[]{Connection.class}, new ConnectionHandler(connection));
        return (Connection)o;
    }

    public class PreparedStatementHandler implements InvocationHandler {

        private PreparedStatement statement;

        private JdbcStatistics jdbcStatistics;

        public PreparedStatementHandler(PreparedStatement statement, JdbcStatistics jdbcStatistics) {
            this.statement = statement;
            this.jdbcStatistics = jdbcStatistics;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            boolean isTargetMethod = false;
            for (String preparedStatementMethod : PREPARED_STATEMENT_METHODS) {
                if (preparedStatementMethod.equals(method.getName())) {
                    isTargetMethod = true;
                }
            }
            Object result = null;
            try {
                result = method.invoke(statement, args);
            } catch (Exception e) {
                if (isTargetMethod) {
                    JdbcCommonCollect.this.error(jdbcStatistics, e);
                }
                throw e;
            } finally {
                if (isTargetMethod) {
                    JdbcCommonCollect.this.end(jdbcStatistics);
                }
            }
            return result;
        }
    }

}
