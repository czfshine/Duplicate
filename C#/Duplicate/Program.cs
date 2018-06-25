using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.IO;
using System.IO.Filesystem.Ntfs;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Duplicate
{
    class Program
    {
        static void Main(string[] args)
        {
            FileTree fileTree = new FileTree();
            fileTree.BuildTree("H");
            Console.WriteLine( fileTree.RootNode.GetHash());
            Console.WriteLine(fileTree.RootNode.GetReadlysize());
            var map= fileTree.FindSameDir();
            int count = 0;
            foreach(var item in map)
            {
                if (item.Value.Count <= 1)
                {
                    continue;
                }
                if (item.Key.Equals("d41d8cd98f00b204e9800998ecf8427e"))
                {
                    continue;
                }
                count++;
                Console.WriteLine("Group " + count);
                foreach(var dir in item.Value)
                {
                    if (dir is DirNode)
                    {
                        Console.WriteLine(((DirNode) dir).Path);
                    }
                }
            }
            Console.ReadKey(true);
        }
       
    }
}
