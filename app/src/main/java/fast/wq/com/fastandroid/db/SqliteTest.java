package fast.wq.com.fastandroid.db;

/**
 * Created by wangqiang on 2016/12/15.
 * Sqlite 是一个小型的，嵌入 关系型数据库
 * 可嵌入 ：不需要引擎响应
 * 无数据类型的数据库：不检查类型
 * 特点： 跨平台的磁盘文件，代码量小，API使用简单
 * <p/>
 * 1 支持类型: Integer, varchar(10) ,float ,double ,char(10) ,text
 * <p/>
 * 2 SQL
 * create table 表名（字段名称， 类型数据 ，约束 ......） 约束，主见，自动增长，大于某个值
 * crate table person (_id Integer primary key  ,  name  varchar(10),  age Integer not null)
 * <p/>
 * drop table 表名
 * drop table person
 * <p/>
 * insert into 表名 [字段1，字段2] values(值1，值2 。。。)
 * insert into person (_id ,age) values(1,20)
 * insert into person values(1,"张三",20)
 * <p/>
 * update 表名 set 字段 = 新值 where 修改条件
 * update person set name="lisi" where _id=1
 * <p/>
 * 删除数据：
 * delete from 表名 where 删除条件
 * delete from person where _id = 2
 * <p/>
 * select 字段名 from 表名 where group by 分组字段 having 筛选条件 order by 排序字段
 * select * from person
 * select _id,name from person
 * select * from person where _id<>1 不等于1
 * select * from person where _id<>1 and age>18
 * select * from person where name like "%小%" 中间含有小  "_小%" 一个字符前后面任意多
 * select * from person where name is null
 * select * from person where age between 10 and 20
 * select * from person where age > 18 order by _id
 */
public class SqliteTest {

    public void createTable() {

    }
}
