# Teste Toyoda - Sura

Api de Teste Sura

## Executar o comando no Terminal:
 ```
 mvn clean package
 docker-compose up
 ```
 
### Prerequisites

- Java 8
- Maven 3.6.0
- Docker
- Docker Compose

### Autenticação
```
http://localhost:8080/api/login

{
    "username": "email",
    "password": "senha"
}
```
** Obter Response Header Authorization

### URL Contexto

```
http://localhost:8080/api/
```

### SWAGGER URL
```
http://localhost:8080/swagger-ui.html
```

### Postman
```
https://www.getpostman.com/collections/778921b0d7b0c71499a6`
```
 
### Versioning

1.0.0

### Authors

* **Marcelo Toyoda** - *Initial work*

### License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details
