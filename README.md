# TP 4: UDP Sockets (Connectionless Mode)

**Course:** Développement d'applications réparties, LSI 3
**Faculty:** Faculté des Sciences de Sfax
**Author:** Houssem Thabet

## Description

A client/server chat application in Java using UDP (`DatagramSocket` and `DatagramPacket`).

- **Step 2:** one-way chat. The server listens on port 1234 and displays the messages sent by a single client.
- **Step 3:** two-way chat. The server keeps a list of client addresses and broadcasts each message to all other clients. Each client uses a thread to receive messages while it sends.

## Project structure

```
src/
 ├── UDPServer.java
 └── UDPClient.java
screenshots/
README.md
```

## How to run

1. Run `UDPServer` first (it listens on port 1234).
2. Run `UDPClient` and enter a username.
3. To test with several clients, run `UDPClient` multiple times (in IntelliJ: *Edit Configurations → Allow multiple instances*).
4. Type a message in one client: it appears in all the other clients.

## Screenshots

### Step 2: One client and the server

![Step 2](screenshots/step2.png)

The client sends `[Username] : message` and the server displays the sender's IP address, port and the message.

### Step 3: Several clients (broadcast)

![Step 3](screenshots/step3.png)

Messages sent by one client are received in real time by all the other clients, but not by the sender.

## Summary

- UDP is a connectionless protocol: there is no handshake and no guarantee of delivery or order.
- Data is exchanged as datagrams (`DatagramPacket`) through a `DatagramSocket`.
- The server has no real connections, so it simulates them by storing the `SocketAddress` of every client that has sent a message (in a `Set`, so there are no duplicates).
- When a message arrives, the server forwards it to every registered client except the sender.
- Each client uses a separate thread to receive messages, so it can send and receive at the same time.
- A client must send a first message (for example "joined the chat") so the server registers its address.
