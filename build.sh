./mvnw clean package -DskipTest
docker build -t configuration-server ./configuration-server
docker build -t discovery-server ./discovery-server
docker build -t gateway-server ./gateway-server
docker build -t payments-service ./payments-service
docker build -t shop-service ./shop-service
