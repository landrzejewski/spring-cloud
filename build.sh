./mvnw clean package -DskipTest
docker build -t configuartion-server ./configuration-server
