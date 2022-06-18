# Docker Setup

1. Create JAR file:
```bash
./gradlew build && java -jar build/libs/safar.jar
```

2. Build image:
```bash
docker build -t <name> .
```

3. Run Container:
```bash
docker run -p 8080:8080 <image-name>
```

