# compare
Java Part (Hibernate, Jsoup...etc.). Please also see the javascript part called compare_api

You have to change database connection details under src > hibernate > Mdxjdbc.java
		String jdbcUrl="jdbc:mysql://localhost:3306/YOURDATABASENAME?useSSL=false";
		String user="YOURUSERNAME";
		String pass="YOURPASSWORD";
    
 You also need to edit database connection settings under src > resources > JDBC  at line 10.
 
 <!-- JDBC Database connection settings -->
        <property name="connection.driver_class">com.mysql.jdbc.Driver</property>
        <property name="connection.url">jdbc:mysql://localhost:3306/YOURDATABASENAME?serverTimezone=UTC</property>
        <property name="hibernate.connection.username">YOURUSERNAME</property>
        <property name="hibernate.connection.password">YOURPASSWORD</property>

You need to have mysql database tables related with entity table under src > Property.java file.
Please see the database table diagram picture.  id is Primary Key in agent table. agent_id is foreign key in property table.

CREATE TABLE `agent` (
  `id` int(10) NOT NULL,
  `agent_name` varchar(50) NOT NULL,
  `agent_phone` char(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `property` (
  `id` int(100) NOT NULL,
  `agent_id` int(10) DEFAULT NULL,
  `img_link` varchar(250) NOT NULL,
  `description` varchar(2250) NOT NULL,
  `location` varchar(100) NOT NULL,
  `longitude` varchar(100) NOT NULL,
  `latitude` varchar(100) NOT NULL,
  `price` varchar(100) NOT NULL,
  `view_link` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
 agent_id is Foreign Key.
 
 JUST RIGHT CLICK APP.JAVA UNDER compare\src\main\java\comparePrice\compare\App.java  AND SELECT RUN AS JAVA APPLICATION.
 
 When you run it, you will see the somethings like;
 
 Session factory built.
Hibernate: insert into property (agent_id, description, img_link, latitude, location, longitude, price, view_link) values (?, ?, ?, ?, ?, ?, ?, ?)
Done!
Hibernate: insert into property (agent_id, description, img_link, latitude, location, longitude, price, view_link) values (?, ?, ?, ?, ?, ?, ?, ?)
Done!
Hibernate: insert into property (agent_id, description, img_link, latitude, location, longitude, price, view_link) values (?, ?, ?, ?, ?, ?, ?, ?)
Done!
Hibernate: insert into property (agent_id, description, img_link, latitude, location, longitude, price, view_link) values (?, ?, ?, ?, ?, ?, ?, ?)
Done!
Please wait while is completed. It scrapes the data from 4 different websites and insert them into database.
