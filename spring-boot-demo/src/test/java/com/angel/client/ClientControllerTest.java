package com.angel.client;

import com.angel.MyApplication;
import com.angel.entity.Client;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.util.NestedServletException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MyApplication.class)
@WebAppConfiguration
public class ClientControllerTest extends AbstractTest {

    @Before
    public void setUp() {
        super.setUp();
    }

    @WithMockUser("angel")
    @Test
    public void getClientById() throws Exception {
        String uri = "http://localhost:8080/user/client/1";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        Client client = super.mapFromJson(content, Client.class);
        assertEquals(1,client.getClientId());
    }

    @WithMockUser("angel")
    @Test
    public void getAllClients() throws Exception {
        String uri = "http://localhost:8080/user/clients";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        Client[] clientsList = super.mapFromJson(content, Client[].class);
        assertTrue(clientsList.length > 0);
    }

    @WithMockUser("angel")
    @Test
    public void addClientTest() throws Exception {
        String uri = "http://localhost:8080/user/addClient";
        Client client = new Client();
        client.setName("Ginger");
        client.setAddress("address");
        String inputJson = super.mapToJson(client);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(201, status);
    }

    @WithMockUser("angel")
    @Test
    public void addVariousClientTest() throws Exception {
        String uri = "http://localhost:8080/user/addVariousClient";
        List<Client> clientList = new ArrayList<>();
        Client client = new Client();
        client.setName("Ginger");
        client.setAddress("address");
        Client client1 = new Client();
        client1.setName("Ginger1");
        client1.setAddress("address1");
        clientList.add(client);
        clientList.add(client1);
        String inputJson = super.mapToJson(clientList);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(201, status);
    }

    @WithMockUser("angel")
    @Test
    public void updateClientTest() throws Exception {
        String uri = "http://localhost:8080/user/updateClient";
        Client client = new Client();
        client.setClientId(1);
        client.setName("Ginger");
        client.setAddress("address");
        String inputJson = super.mapToJson(client);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

    @WithMockUser("any")
    @Test(expected = NestedServletException.class)
    public void deleteClientTestFail() throws Exception {
        String uri = "http://localhost:8080/user/deleteClient/1";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
    }


}