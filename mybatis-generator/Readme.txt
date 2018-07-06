通过配置资源文件 generatorConfig.xml 来控制生成文件的各个目录
运行 mvn clean mybatis-generator:generate 命令生成需要的文件。

生成后的内容在target/generated-sources/mybatis-generator目录下


注意：数据库的用户名和密码一定要正确，否则maven目标执行失败。


对应的生成table配置的代码生成如下：
public class GenTables {

    private static String user = "root";
    private static String password = "root";
    private static String url = "jdbc:mysql://localhost:3306/order_test?characterEncoding=utf-8";

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        List<String> tableNames = new ArrayList<>();
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection(url, user, password);
        DatabaseMetaData dbMetaData = conn.getMetaData();
        ResultSet tables = dbMetaData.getTables(null, null, "", new String[]{"TABLE"});
        while (tables.next()) {
            tableNames.add(tables.getString(3)); // 获取表名称
        }
        tables.close();
        conn.close();
        Map<String, String> tableNameMapClassName = converToMap(tableNames);
        printResult(tableNameMapClassName);


    }
    /**
     * 将分隔的字符串转换成驼峰命名的字符串
     *
     * @param tableName
     * @return
     */
    public static String coverTableNameToClassName(String tableName) {
        if (tableName == null || "".equals(tableName.trim())) {
            return null;
        }
        StringTokenizer sTokenizer = new StringTokenizer(tableName.trim(),"_");
        StringBuffer className = new StringBuffer();
        while (sTokenizer.hasMoreTokens()) {
            String token = sTokenizer.nextToken();
            className.append(token.substring(0, 1).toUpperCase());
            if (token.length() > 1) {
                className.append(token.substring(1));
            }
        }
        return className.toString();
    }
    /**
     * 将表名转换成表名对应类名的字典类
     *
     * @param tableName
     */
    public static Map<String, String> converToMap(List<String> tableName) {
        return tableName.parallelStream().collect(Collectors.toMap(vo -> vo, vo -> { return coverTableNameToClassName(vo);}));
    }
    public static void printResult(Map<String, String> tables) {
        for (Map.Entry<String, String> entry : tables.entrySet()) {
            System.out.println("<table tableName=\"" + entry.getKey() + "\" domainObjectName=\"" + entry.getValue() + "\" />");
        }
    }
}