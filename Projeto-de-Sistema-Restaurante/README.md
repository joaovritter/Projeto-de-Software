# 🍽️ Madera Restaurante

Sistema de gerenciamento para restaurantes desenvolvido em Java com Spring Boot.

## 📋 Descrição

O Madera é um sistema completo para gerenciamento de restaurantes, permitindo o controle de mesas, pedidos, produtos e pagamentos. O sistema foi desenvolvido para facilitar o dia a dia dos funcionários e melhorar a experiência dos clientes.

## 🚀 Funcionalidades

### Mesas
- Cadastro e gerenciamento de mesas
- Visualização do status de ocupação
- Histórico de pedidos por mesa

### Produtos
- Cadastro de produtos com imagens
- Categorização de produtos
- Controle de preços
- Upload de imagens para os produtos

### Pedidos
- Criação de pedidos por mesa
- Acompanhamento do status do pedido
- Cálculo automático do valor total
- Opção de taxa de garçom
- Histórico de pedidos

### Status dos Pedidos
- ABERTO: Pedido recém-criado
- EM_PRODUCAO: Pedido em preparação
- FINALIZADO: Pedido concluído e pago
- CANCELADO: Pedido cancelado

## 🛠️ Tecnologias Utilizadas

- Java 17
- Spring Boot 3.4.5
- Spring Data JPA
- MySQL
- Thymeleaf
- Bootstrap 5
- Lombok
- Maven

## 📦 Pré-requisitos

- JDK 17 ou superior
- Maven
- MySQL
- IDE (recomendado: IntelliJ IDEA ou Eclipse)

## 🔧 Instalação

1. Clone o repositório:
```bash
git clone https://github.com/seu-usuario/madera-restaurante.git
```

2. Configure o banco de dados MySQL:
- Crie um banco de dados chamado `madera`
- Configure as credenciais no arquivo `application.properties`

3. Execute o projeto:
```bash
mvn spring-boot:run
```

4. Acesse o sistema:
```
http://localhost:8080
```

## 📝 Configuração do Banco de Dados

No arquivo `src/main/resources/application.properties`, configure:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/madera
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
```

## 🎨 Interface

O sistema possui uma interface moderna e responsiva, desenvolvida com Bootstrap 5, que se adapta a diferentes tamanhos de tela.

## 👥 Contribuição

1. Faça um Fork do projeto
2. Crie uma Branch para sua Feature (`git checkout -b feature/AmazingFeature`)
3. Commit suas mudanças (`git commit -m 'Add some AmazingFeature'`)
4. Push para a Branch (`git push origin feature/AmazingFeature`)
5. Abra um Pull Request

## 📄 Licença

Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

## ✨ Autores

- Seu Nome - [seu-usuario](https://github.com/seu-usuario)

## 🙏 Agradecimentos

- Spring Boot Team
- Bootstrap Team
- Comunidade Java 