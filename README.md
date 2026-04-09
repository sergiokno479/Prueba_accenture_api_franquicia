Backend de Gestión de Franquicias (Reactive & Clean Architecture)
Este sistema ofrece una solución escalable para la administración centralizada de franquicias, sucursales y stock de productos. Está diseñado bajo los principios de Arquitectura Limpia, priorizando el mantenimiento a largo plazo y la resiliencia del software.

Tech Stack
Core: Java 21 & Spring Boot 4.x.

Paradigma: Programación Reactiva con Spring WebFlux (I/O no bloqueante).

Persistencia: Spring Data R2DBC sobre MySQL 8.0.

Infraestructura: Docker (Contenerización) y Terraform (IaC para DigitalOcean).

Calidad: JUnit 5 y Mockito para cobertura de pruebas unitarias.

Diseño Arquitectónico
Se implementó una Arquitectura Hexagonal para aislar el dominio de los cambios tecnológicos externos:

Domain: Contiene las entidades de negocio y las definiciones de puertos (interfaces). No tiene dependencias externas.

Application: Orquestación de la lógica mediante casos de uso.

Infrastructure: Implementación de adaptadores (API REST, persistencia R2DBC) y configuración del framework.

Guía de Ejecución Local
1. Vía Docker Compose (Método sugerido)
Para levantar el entorno completo (Base de datos + API) de forma automatizada:

Acceder al directorio del servicio:

Bash
cd franquicia
Construir el artefacto:

Bash
./mvnw clean package -DskipTests
Desplegar contenedores:

Bash
docker-compose up -d --build
Punto de enlace: http://localhost:8080

2. Vía IDE (Desarrollo)
Contar con una instancia de MySQL y crear el esquema franchise_db.

Verificar credenciales en src/main/resources/application.properties.

Ejecutar la clase FranquiciaApplication.java.

Despliegue en Cloud (Terraform)
El repositorio incluye la definición de infraestructura necesaria para desplegar en DigitalOcean:

Situarse en el directorio correspondiente:

Bash
cd terraform
Configurar el acceso en un archivo terraform.tfvars:

Terraform
do_token = "TU_API_TOKEN"
Provisionar recursos:

Bash
terraform init
terraform apply
Calidad y Testing
Para ejecutar el conjunto de pruebas unitarias (centradas en validaciones de negocio y gestión de inventario):

Bash
./mvnw test
