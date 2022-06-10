To run:
1. Make sure you have [Docker](https://www.docker.com/products/docker-desktop/) installed.
2. Open Android Studio terminal.
3. Run the following commands:
   1. `docker build -t final-project-mssql .` - builds mssql environment in docker container. 
   2. `docker run -p 1433:1433 -t final-project-mssql` - runs the container on port 1433.
   3. wait until `DATABASE INITIALIZED` message is shown for the database to finish populating.
   4. Run project via Android Studio Emulator locally.