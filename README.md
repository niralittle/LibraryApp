LibraryApp
==========

A student project of a library service application on Java using different technologies: sockets, RMI and web.

In order to launch the Library app (desktop version):

1. Launch Derby from the working catalog: java -jar "<i>path_to_jdk</i>\db\lib\derbyrun.jar" server start
2. Run the create_database.sql script to create and fill tables with data
3. In Client.properties file choose which implementation you want to use (sockets or rmi)<br>
3a. Run SocketServer.class with parameter "12350" for socket port<br>
3b. Run 'start rmiregistry' in root dir of compiled classes then RMIServer.class<br>
5. Run MainWindow.class to open UI:<br>
<ul>
	<li> user credentials: user / password</li>
	<li> admin credentials: admin / pass</li>
</ul>