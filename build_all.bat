call mvn clean
call mvn compile
call mvn install -DskipTests

cd oauth-registry-ms
docker images
docker build -t oauth-registry-ms:1.0.0 .
docker images
cd ..

cd oauth-api-gateway
docker images
docker build -t oauth-api-gateway:1.0.0 .
docker images
cd ..

cd oauth-user-ms
docker images
docker build -t oauth-user-ms:1.0.0 .
docker images
cd ..

docker-compose up