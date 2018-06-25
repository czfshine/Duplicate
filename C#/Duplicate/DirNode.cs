using System;
using System.Collections.Generic;
using System.Linq;
using System.Security.Cryptography;
using System.Text;
using System.Threading.Tasks;

namespace Duplicate
{
    class DirNode : TreeNode
    {   
        private void SortChildrenBySize()
        {
            Children.Sort();
            Sorted = true;
        }
        private bool Sorted { set; get; }

        protected override string SetAndCreateHash()
        {
            if(Sorted!=true) SortChildrenBySize();
            StringBuilder input = new StringBuilder();
            foreach (TreeNode node  in Children)
            {
                input.Append(node.GetHash());
            }
            MD5 md5Hash = MD5.Create();
            byte[] data = md5Hash.ComputeHash(Encoding.UTF8.GetBytes(input.ToString()));

            StringBuilder sBuilder = new StringBuilder();

            // Loop through each byte of the hashed data 
            // and format each one as a hexadecimal string.
            for (int i = 0; i < data.Length; i++)
            {
                sBuilder.Append(data[i].ToString("x2"));
            }
            // Return the hexadecimal string.
            Hash = sBuilder.ToString();
            return Hash;
        }
    }
}
