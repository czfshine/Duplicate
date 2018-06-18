package cn.czfshine.duplicate; 

import org.junit.Test; 
import org.junit.Before; 
import org.junit.After; 

/** 
* FileList Tester. 
* 
* @author <Authors name> 
* @since <pre>06/17/2018</pre> 
* @version 1.0 
*/ 
public class FileListTest { 

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: createList(String pathstr) 
* 
*/ 
@Test
public void testCreateList() throws Exception { 
//TODO: Test goes here... 
    FileList.createList("H:\\");
}

/** 
* 
* Method: preVisitDirectory(Path dir, BasicFileAttributes attrs) 
* 
*/ 
@Test
public void testPreVisitDirectory() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: visitFile(Path file, BasicFileAttributes attrs) 
* 
*/ 
@Test
public void testVisitFile() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: visitFileFailed(Path file, IOException exc) 
* 
*/ 
@Test
public void testVisitFileFailed() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: postVisitDirectory(Path dir, IOException exc) 
* 
*/ 
@Test
public void testPostVisitDirectory() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: setCurdir(VirtualDirs curdir) 
* 
*/ 
@Test
public void testSetCurdir() throws Exception { 
//TODO: Test goes here... 
} 


} 
