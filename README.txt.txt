
Prerequisites : JDK 1.6 and Apache Commons io jar : commons-io-2.4.jar

Due to time constraints I had to harcode file paths:

Please set up 2 directories/folders under C:

C:/FileStore/input
C:/FileStore/processed

Execute Main.java

Main initiates the platform processing by starting the Global Event Initiator.
Initiator starts up the FileProcessor Executor scheduler which is the producer of files.
Also it starts an Event Manager scheduler for managing consumers.