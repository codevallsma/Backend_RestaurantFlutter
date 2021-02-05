# Restaurant-Server
To set up the Minder backend some steps have to be followed:<br>
**1-Create the database in your MySQL command line:**<br>
    -Just like this:<br>
    create database <nameOfYouRDatabase>;<br>
    In our case we set the Database to Restaurant<br>
      -<kbd/>**create database Restaurant**</kbd>;
    
**2- Open the Project with IntellIJ.**
- If a maven message pops up, click <kbd/>**Enable-AutoImport**</kbd>
    
**3-Set up your application.propierties file:**
  - *3.1) In the "spring.datasource.url" write the name of your database.* 
      Just like this:
      <kbd/>**spring.datasource.url=jdbc:mysql://localhost:3306/\"NameOfYourDatabase\"?useSSL=false&allowPublicKeyRetrieval=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC**</kbd><br>
      In our case:<br>
jdbc:mysql://localhost:3306/Restaurant?useSSL=false&allowPublicKeyRetrieval=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Europe/Madrid<br><br>
  - *3.2) Set the database user owner in "spring.datasource.user", in our case we are going to stick with the root user*<br>
          <kbd/>**spring.datasource.username=yourUser**</kbd><br><br>
  - *3.2) Set the passsword of your root user or the database user owner in "spring.datasource.password. In our case we will use the root password"*<br>
         <kbd/>**spring.datasource.password=YourPassword**</kbd><br><br>
  - *3.4) Set spring.jpa.hibernate.ddl-auto to create:*<br>
         <kbd/>**spring.jpa.hibernate.ddl-auto=create**</kbd><br><br>
**4- Right click with your mouse the RestaurantAplication file and click "Run 'RestaurantAplication'"**      
