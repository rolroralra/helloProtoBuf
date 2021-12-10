package com.example.proto;

import org.junit.jupiter.api.*;

import java.io.*;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SampleProtoTest {
    @Order(1)
    @DisplayName("1. protoc를 통해 Protobuf 직렬화를 할 수 있다.")
    @Test
    void test_serialize_to_binary_file() throws IOException, ClassNotFoundException {
        // Given
        SampleProto.Person person =
                SampleProto.Person.newBuilder()
                        .setId(1001)
                        .setEmail("rolroralra@naver.com")
                        .setName("rolroralra")
                        .addNumbers("20211011")
                        .build();

        assertNotNull(person);
        System.out.println(person);

        // When
        String filePath = Paths.get("src", "test", "resources", "person.bin").toFile().getAbsolutePath();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        FileOutputStream fos = new FileOutputStream(filePath);

        oos.writeObject(person);

        byte[] byteArray = baos.toByteArray();
        System.out.println(Arrays.toString(byteArray));
        fos.write(byteArray);


        // Then
        assertThat(byteArray).isNotEmpty();

        File file = new File(filePath);
        assertThat(file).exists();

    }

    @Order(2)
    @DisplayName("2. protoc를 통해 Protobuf 역직렬화를 할 수 있다.")
    @Test
    void test_deserialize_from_binary_file() throws IOException, ClassNotFoundException {
        // Given
        ClassLoader classLoader = getClass().getClassLoader();

        String filePath = Paths.get("src", "test", "resources", "person.bin").toFile().getAbsolutePath();
        FileInputStream fis = new FileInputStream(filePath);
        ObjectInputStream ois = new ObjectInputStream(fis);

        // When
        SampleProto.Person person = (SampleProto.Person) ois.readObject();

        // Then
        assertThat(person)
                .isNotNull()
                .isInstanceOf(SampleProto.Person.class);

        System.out.println(person);
    }

}
