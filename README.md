Build the application:

`./gradlew clean build`

Build and start the containers:

`docker-compose up --build`

At this point the java container will throw some errors because the database is not set up yet.

Create fda database:

`docker exec -it psql-fda psql -U postgres -f /database/db_setup.sql`

Restart the containers:

`docker-compose restart`

The API can be accessed through the Swagger UI located at localhost:8080/fda-service/swagger-ui.html