cd oauth-registry-ms
docker images
docker build -t oauth-registry-ms:1.0.0 .
#docker tag oauth-registry-ms:1.0.0 localhost:5000/oauth-registry-ms:1.0.0
#docker push localhost:5000/oauth-registry-ms:1.0.0
docker images
cd ..

cd oauth-api-gateway
docker images
docker build -t oauth-api-gateway:1.0.0 .
#docker tag oauth-api-gateway:1.0.0 localhost:5000/oauth-api-gateway:1.0.0
#docker push localhost:5000/oauth-api-gateway:1.0.0
docker images
cd ..

cd oauth-user-ms
docker images
docker build -t oauth-user-ms:1.0.0 .
#docker tag oauth-user-ms:1.0.0 localhost:5000/oauth-user-ms:1.0.0
#docker push localhost:5000/oauth-user-ms:1.0.0
docker images
cd ..