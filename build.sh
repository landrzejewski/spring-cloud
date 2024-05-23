./mvnw clean package -DskipTest
docker build -t configuration-server ./configuration-server
