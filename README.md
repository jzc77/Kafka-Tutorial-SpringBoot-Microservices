# Kafka Tutorial - SpringBoot Microservices
- Following tutorial at: www.youtube.com/watch?v=SqVfCyfCJqw&amp;t
- Kafka Quickstart: https://kafka.apache.org/quickstart

## Windows CLI commands
- Commands to start ZooKeeper and Kafka broker (use different terminals for each command):
  1. Start the ZooKeeper service: `bin/windows/zookeeper-server-start.bat config/zookeeper.properties`
  2. Start the Kafka broker service: `bin/windows/kafka-server-start.bat config/server.properties`
- Optional commands (Note that `jc` is the topic name I have defined in KafkaTopicConfig.java)
  - Run the console consumer client to read the events: `bin/windows/kafka-console-consumer.bat --topic jc --from-beginning --bootstrap-server localhost:9092`
  - List out all topics: `bin/windows/kafka-topics.bat --bootstrap-server localhost:9092 --list`
  - Delete a topic: `bin/windows/kafka-topics.bat --bootstrap-server localhost:9092 --delete --topic jc`

## Kafka notes

### General
- Each message in a Kafka topic is a key-value (data) pair.
  - **Key** (optional): used for partitioning messages across Kafka topics
  - **Value**: main payload of the message

- Before data (key and value) is stored in Kafka topics, the data needs to be serialized (converted into a sequence of bytes) (KafkaProducerConfig.java).
- This is because Kafka is a distributed system, so data needs to be sent in a form that can be transmitted across the network.
- Producers produce serialized data.
- Consumers deserialize the data (key and value) with a corresponding deserializer in order to interpret the bytes.

### Kafka services
- A **kafka broker** arranges transactions between producers and consumers. A Kafka **cluster** is a collection of one or more Kafka brokers.
- **Zookeeper** manages metadata and is used by kafka brokers. E.g. Keeps track of which brokers are part of the Kafka cluster

## Other notes
- Java records
  - A Java class could be a Class, Interface, **Record**, Enum, or Annotation.
    - Records are a concise representation of immutable data class.
    - <details>
        <summary>Creating an immutable class <b>without</b> records:</summary>
            
        ```java
            
        public final class Account {
            
          private final String name;
          private final int id;
          private final String type;
            
          public Account(String name, int id, String type) {
            this.name = name;
            this.id = id;
            this.type = type;
          }
            
          public String name() {
            return name;
          }
            
          public int id() {
            return id;
          }
            
          public String type() {
            return type;
          }
            
          @Override
          public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Account that = (Account) o;
            return id == that.id && Objects.equals(name, that.name) && Objects.equals(type, that.type);
          }
            
          @Override
          public int hashCode() {
            return Objects.hash(name, id, type);
          }
            
          @Override
          public String toString() {
            return "Account{" +
                    "name='" + name + '\'' +
                    ", id=" + id +
                    ", type='" + type + '\'' +
                    '}';
          }
        }
        ```
      </details>
    - Creating an immutable class **with** records:
      ```java
      public record Account(String name, int id, String type) {}
      ```
  - Several methods are automatically generated: a constructor, getters, equals(), hashCode(), and toString().
  - Source: https://medium.com/@reetesh043/record-java-new-feature-daf97797bf3a