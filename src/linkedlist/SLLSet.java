package linkedlist;

public class SLLSet {
    private int size;
    private SLLNode head;
    
    public SLLSet()
    {
        size = 0;
        head = null;
    }
    
    public SLLSet(int[] sortedArray)
    {
        if(sortedArray.length > 0)
        {
            head = new SLLNode(sortedArray[0],null);
            size = 1;
            for(int i = sortedArray.length-1; i > 0;i--)
            {
                SLLNode p = new SLLNode(sortedArray[i],head.next);
                head.next = p;
                size++;
            }
        }
        else
        {
            size = 0;
            head = null;
        }
    }
    
    public int getSize()
    {
        return size;
    }
    
    public SLLSet copy()
    {
        SLLSet hello = new SLLSet();
       
        if (head != null)
        {
            hello.head = new SLLNode(this.head.value,null);
            hello.size++;
            for(SLLNode p= this.head.next; p!= null; p=p.next)
            {
                hello.size++;
                SLLNode z = hello.head;
                while(z.next!= null)
                {
                    z= z.next;
                }
                SLLNode q = new SLLNode(p.value,z.next);
                z.next = q;
            }
        }
        return hello;
    }
    
    public boolean isIn(int v)
    {
        for(SLLNode p = head; p!= null; p=p.next)
        {
            if (p.value == v)
            {
                return true;
            }
        }
        return false;
    }
    
    public void add(int v)
    {
        if(head==null)
        {
            head = new SLLNode(v,null);
            size++;
        }
        else if(v< head.value)
        {
            SLLNode q = new SLLNode(head.value,head.next);
            head.value = v;
            head.next = q;
            size++;
        }
        else
        {
            for(SLLNode p = head; p != null; p=p.next)
            {
                if(p.value == v)
                {
                    break;
                }
                if(v>p.value && (p.next == null || v<p.next.value))
                { 
                    SLLNode q = new SLLNode(v,p.next);
                    p.next = q;
                    size++;
                    break;         
                }
            }
        }
    }
    
    public void remove(int v)
    {
        if (head!= null && head.value== v)
        {
            head = head.next;
            size--;
        }
        else
        {
            for(SLLNode p = head; p != null; p = p.next)
            {
                if (p.next == null)
                {
                    break;
                }
                if (p.next.value == v)
                {
                    p.next = p.next.next;
                    size--;
                    break;
                }

            }
        }
    }
    public SLLSet union(SLLSet s)
    {
        SLLSet u = this.copy();
        for(SLLNode p = s.head;p != null;p=p.next)
        {
            u.add(p.value);
        }
        return u;
    }
    
    public SLLSet intersection(SLLSet s)
    {
        SLLSet i = this.copy();
        for(SLLNode p = i.head; p!= null; p=p.next)
        {
            if(s.isIn(p.value)==false)
            {
                i.remove(p.value);
            }
        }
        return i;
    }
    
    public SLLSet difference(SLLSet s)
    {
        if (this.size>s.size)
        {
            SLLSet d = this.copy();
            for(SLLNode p = s.head; p!= null; p=p.next)
            {
                if(d.isIn(p.value)==true)
                {
                    d.remove(p.value);
                }
            }
            return d;
        }
        else
        {
            SLLSet d = s.copy();
            for(SLLNode p = this.head; p!= null; p=p.next)
            {
                if(d.isIn(p.value)==true)
                {
                    d.remove(p.value);
                }
            }
            return d;
        }
        
    }
    
    public String toString()
    {
        SLLNode p = head;
        if (p != null)
        {
            String hello = "[" + p.value;
            for(p=p.next;p!=null;p=p.next)
            {
                hello = hello + ","+p.value;
            }
            hello = hello + "]";
            return hello;
        }
        return "Empty List";
    }
    
    public static SLLSet union(SLLSet[] sArray)
    {
        SLLSet u = sArray[0].copy();
        for(int i = 1;i<sArray.length;i++)
        {
            u = u.union(sArray[i]);
        }
        return u;
    }
            
}
