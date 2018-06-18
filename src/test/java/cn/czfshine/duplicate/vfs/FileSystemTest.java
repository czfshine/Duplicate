package cn.czfshine.duplicate.vfs;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After; 

/** 
* FileSystem Tester. 
* 
* @author <Authors name> 
* @since <pre>06/17/2018</pre> 
* @version 1.0 
*/ 
public class FileSystemTest { 

    FileSystem fs;
@Before
public void before() throws Exception {

    FileSystem fileSystem = new FileSystem("1");

    VirtualDirs root = fileSystem.getRoot();
    root.addFile(new VirtualFiles("2",3,root));
    root.addFile(new VirtualFiles("3",4,root));

    VirtualDirs c1 = new VirtualDirs("5",root);

    c1.addFile(new VirtualFiles("6",7,c1));

    root.addDir(c1);

    fs=fileSystem;
}

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: getRoot() 
* 
*/ 
@Test
public void testGetRoot() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: setRoot(VirtualDirs root) 
* 
*/ 
@Test
public void testSetRoot() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: dumpFile(String path) 
* 
*/ 
@Test
public void testDumpFile() throws Exception { 
//TODO: Test goes here... 

    fs.dumpFile("test.dat");
}

/** 
* 
* Method: loadFromFile(String path) 
* 
*/ 
@Test
public void testLoadFromFile() throws Exception { 
//TODO: Test goes here... 
    fs.dumpFile("test.dat");
    FileSystem fileSystem = FileSystem.loadFromFile("test.dat");
    assertEquals("1",fileSystem.getRoot().getPathname());

}


} 
