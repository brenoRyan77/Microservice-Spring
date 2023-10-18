# Microservice com Spring

Este é um projeto de exemplo que demonstra um fluxo de cadastro de usuário e o envio de email através de Microserivice e Sistema de Mensageria e Fila com RabbitMQ 

Este guia mostra como configurar um produtor e um consumidor de mensagens RabbitMQ em um aplicativo Spring Boot.

## Pré-requisitos

- [Spring Boot](https://spring.io/projects/spring-boot) configurado no seu ambiente de desenvolvimento.
- Um servidor RabbitMQ configurado e em execução (você pode usar uma instância local ou um serviço em nuvem).

# Passos para Configurar um Fluxo de Mensageria com CloudAMQP

A seguir, descrevemos os passos necessários para configurar um fluxo de mensageria com RabbitMQ usando o serviço CloudAMQP.

## 1. Criar uma Conta no CloudAMQP

- Acesse o site do [CloudAMQP](https://www.cloudamqp.com/).
- Crie uma conta se ainda não tiver uma ou faça login.

## 2. Criar uma Instância do RabbitMQ

- Após fazer login no CloudAMQP, clique em "Create instance" para criar uma nova instância do RabbitMQ.
- Escolha um plano (grátis ou pago) e configure os detalhes da instância, como nome, região, etc.
- Clique em "Create" para criar a instância.

## 3. Configure a Instância

- Após a criação da instância, você verá informações importantes, como a URL de conexão, nome de usuário e senha. Anote essas informações, pois você precisará delas para conectar seu aplicativo ao RabbitMQ hospedado na nuvem.


## Configurando o Spring Boot

1. Adicione as dependências necessárias ao seu arquivo `pom.xml` (Maven) ou `build.gradle` (Gradle) para o Spring Boot e RabbitMQ. Substitua as dependências Spring Boot Starter AMQP e Spring Boot Starter Web:

### Maven

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-amqp</artifactId>
</dependency>
```

### Gradle

```xml
implementation 'org.springframework.boot:spring-boot-starter-amqp'
```


### Configure o RabbitMQ 

no arquivo `application.properties` ou `application.yml`:

```yaml
spring.rabbitmq.addresses=SEU-HOST-RABBITMQ
broker.queue.name=NOME_DA_FILA
```

### Criando um Producer

```java
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private Queue queue;

    @GetMapping("/enviar-mensagem")
    public String enviarMensagem() {
        String mensagem = "Esta é uma mensagem de exemplo.";
        rabbitTemplate.convertAndSend(queue.getName(), mensagem);
        return "Mensagem enviada: " + mensagem;
    }
}

```

### Criando um Consumer

```java
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer {

    @RabbitListener(queues = "sua-fila")
    public void receberMensagem(String mensagem) {
        System.out.println("Mensagem recebida: " + mensagem);
    }
}

```

Inicie seu aplicativo Spring Boot e acesse http://localhost:8080/enviar-mensagem para enviar uma mensagem para a fila. O consumidor processará a mensagem assim que ela chegar à fila.

Este é um exemplo simples de como configurar um produtor e um consumidor com Spring Boot e RabbitMQ. Personalize e expanda conforme suas necessidades específicas, ajustando as configurações e detalhes de conexão de acordo com a sua instância RabbitMQ.

## Considerações

Este guia forneceu um exemplo básico de como configurar um fluxo de mensageria com RabbitMQ usando o Spring Boot. Com um produtor e um consumidor simples, você aprendeu a:

- Configurar o Spring Boot para se comunicar com um servidor RabbitMQ.
- Enviar mensagens para uma fila RabbitMQ.
- Receber e processar mensagens a partir da mesma fila.

Lembre-se de que este é um ponto de partida. Em um ambiente de produção real, você pode personalizar e expandir essa estrutura para atender às necessidades específicas do seu projeto. Além disso, considere aspectos como segurança, gerenciamento de erros e escalabilidade ao implementar sistemas de mensageria mais complexos.

Para obter mais informações sobre o Spring Boot e o RabbitMQ, consulte a documentação oficial e as bibliotecas cliente específicas para sua linguagem de programação.
