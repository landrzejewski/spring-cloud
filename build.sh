./mvnw clean package -DskipTest
docker build -t configuration-server ./configuration-server
docker build -t discovery-server ./discovery-server
