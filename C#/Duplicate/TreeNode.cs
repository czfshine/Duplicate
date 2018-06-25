using System;
using System.Collections;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Duplicate
{
    abstract class TreeNode : IComparable
    {

        public String Path { get; set; }

        private long size;

        public TreeNode Parent { get; set; }
        public long GetSize()
        {
            return size;
        }

        public void SetSize(long value)
        {
            size = value;
        }

        public ArrayList Children { get; set; }

        public TreeNode()
        {
            Children = new ArrayList();
            Path = "";
            SetSize(0);
        }

        public void AddChildren(TreeNode node)
        {   if(node != this)//根目录的父节点是它本身
            {
                Children.Add(node);
                node.Parent = this;
            }
            
        }
        protected String Hash { set; get; }
        protected abstract String SetAndCreateHash();
        public  String GetHash()
        {   if(Hash==null || Hash == "")
            {
                
                SetAndCreateHash();
            }
          
            return Hash;
        }



        public int CompareTo(object obj)
        {
           
            long a = ((TreeNode)obj).GetSize();
            long b = GetSize();
            return a > b ? 1 : a < b ? -1 : 0;
        }
        private long Rsize = -1;
        public long GetReadlysize()
        {
            if (this is FileNode)
            {
                Rsize = size;
            }
            if (Rsize == -1)
            {
                foreach(TreeNode node in Children)
                {
                    Rsize += node.GetReadlysize();
                }
            }

            return Rsize;
        }
    }
}
