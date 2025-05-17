Atenção para a porta do banco, 5433.
docker run --name pg17 \
-e POSTGRES_PASSWORD=root \
-p 5433:5432 \
-d postgres:17