# Kafka Tutorial - SpringBoot Microservices
Following tutorial at: www.youtube.com/watch?v=SqVfCyfCJqw&amp;t

# Notes

- Each message in a Kafka topic is a key-value (data) pair.
  - **Key** (optional): used for partitioning messages across Kafka topics
  - **Value**: main payload of the message

- Before data (key and value) is stored in Kafka topics, the data needs to be serialized (converted into a sequence of bytes) (KafkaProducerConfig.java).
- This is because Kafka is a distributed system, so data needs to be sent in a form that can be transmitted across the network.
- Producers produce serialized data.
- Consumers deserialize the data (key and value) with a corresponding deserializer in order to interpret the bytes.