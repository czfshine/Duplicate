using System;
using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using System.IO;
using System.IO.Filesystem.Ntfs;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Duplicate
{
    class FileTree
    {
        private String Path;
 
        private IEnumerable<INode> GetAllNodesFromDrive()
        {
            DriveInfo driveToAnalyze = new DriveInfo(Path);

            NtfsReader ntfsReader =
                new NtfsReader(driveToAnalyze, RetrieveMode.All);

            IEnumerable<INode> nodes =
                ntfsReader.GetNodes(driveToAnalyze.Name);
            return nodes;
        }
        private TreeNode[] AllTreeNodes;


        private TreeNode CreateNode(INode node)
        {
            TreeNode tnode = (node.Attributes & Attributes.Directory) != 0 ? 
                new DirNode() 
                : (TreeNode)new FileNode();
            SetNodeInfo(tnode, node);
            return tnode;
        }

        private void SetNodeInfo(TreeNode tnode,INode inode)
        {
            tnode.Path = inode.FullName;
            tnode.SetSize((long)inode.Size);

        }
        public DirNode RootNode { get; set; }
        public void BuildTree(String path)
        {
            Path = path;

            var nodes = GetAllNodesFromDrive();
            INode maxnode = nodes.Last();
            AllTreeNodes = new TreeNode[maxnode.NodeIndex+1];

            foreach(INode node in nodes)
            {
                if (AllTreeNodes[node.ParentNodeIndex] == null)
                {
                    AllTreeNodes[node.ParentNodeIndex] = new DirNode();
                }
                if (AllTreeNodes[node.NodeIndex] == null)
                {   
                    
                    AllTreeNodes[node.NodeIndex] = CreateNode(node);
                }
                if (AllTreeNodes[node.NodeIndex].GetSize() == 0)
                {
                    SetNodeInfo(AllTreeNodes[node.NodeIndex], node);
                }

                AllTreeNodes[node.ParentNodeIndex].AddChildren(
                    AllTreeNodes[node.NodeIndex]);
            }
            nodes = null;

            RootNode =(DirNode) AllTreeNodes[5];
        }

        public FileTree()
        {
            SameGroup = new Dictionary<string, ArrayList>();
        }
        private Dictionary<String, ArrayList> SameGroup;

        private void WalkTree(TreeNode node,Func<TreeNode ,int> WalkCall)
        {
            WalkCall(node);
            foreach(TreeNode cnode in node.Children)
            {
                WalkTree(cnode,WalkCall);
            }
        }
        
        private int SameWalkCall(TreeNode node)
        {
            String hash;
            
             hash = node.GetHash();
 

            if (node is FileNode)
                return 0;
            if (!SameGroup.ContainsKey(hash))
            {
                SameGroup[hash] = new ArrayList();
            }
            SameGroup[hash].Add(node);
            return 10;
        }
    
     
    
        public Dictionary<String, ArrayList> FindSameDir()
        {
            WalkTree(RootNode,SameWalkCall);
            SameGroup.Remove("dcfcd07e645d245babe887e5e2daa016");
            SameGroup.Remove("d41d8cd98f00b204e9800998ecf8427e");
            foreach (var item in SameGroup)
            {
                if (item.Value.Count <= 1)
                {
                    continue;
                }
            
               
                var list = item.Value;
                ArrayList toremove = new ArrayList();
                
                foreach (var dir in list)
                {   if (toremove.Contains(dir))
                        continue;
                    //if (((TreeNode)dir).GetSize()==0)
                    //    continue;
                    foreach (var dir2 in list)
                    {
                        if (toremove.Contains(dir2))
                            continue;
                        if (dir == dir2)
                            continue;
                        TreeNode a = (TreeNode)dir;
                        TreeNode b = (TreeNode)dir2;
                        if (a.Parent != null &&  b.Parent!=null)
                        {
                            if (a.Parent.GetHash().Equals(b.Parent.GetHash()))
                            {   if(!toremove.Contains(a))
                                toremove.Add(a);
                                if(!toremove.Contains(b))
                                toremove.Add(b);
                            }
                        }
                    }

                }
                foreach(var node in toremove)
                {
                    if (list.Contains(node))
                    {
                     

                        list.Remove(node);
                    }
                   
                }
                
            }
            return SameGroup;
        }
    }
}
