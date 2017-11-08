import java.io.*;
class library
{
     static String stack_name[]= new String[10000];//stores the name
    static long stack_rollno[]=new long[10000];//stores the roll_no
     static int stack_date[]=new int[10000];//stores the date of book issuse
    static int stack_dueDate[]=new int[10000];//stores the due date of book
    static String queue_nmbk[]=new String[10000];//stores the name of book
    static int stack_fine[]=new int[10000];//store the total fine
    static int count_seats;//to count no of seats filled;
    static int top=-1,front=-1,rear=-1,size=0;;
    InputStreamReader isr= new InputStreamReader(System.in);
    BufferedReader br = new BufferedReader(isr);
    public static void main()throws IOException
    {
        InputStreamReader isr= new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        System.out.println("WELCOME TO LIBRARY PORTAL");
        System.out.println("1.Lending Section \n 2.Reference Section");
        int c=Integer.parseInt(br.readLine());
        library ob= new library();
        if(c==1)
        {
            int ch=0;
            do
            {
                System.out.println("1.ENTER DETAILS \n 2.DUE DATE OF BOOK \n 3. FINE ACCUMULATION \n 4. TIMINGS OF LIBRARY \n 5. SEATS AVAILABLE \n 6.List of books \n 7.Add new Books \n 8.Dispaly the members \n 9.EXIT");
                ch=Integer.parseInt(br.readLine());
                switch(ch)
                {
                    case 1:
                    ob.details();
                    break;
                    case 2:
                    System.out.println("Enter the name of the person whose book's due date you want to check");
                    String nper=br.readLine();
                    ob.due_date();
                    for(int i=0;i<top;i++)
                    {
                      if(stack_name[i]==nper)
                      {
                         System.out.println("DUE DATE :-"+stack_dueDate[i]); 
                      }
                    }
                    break;
                    case 3:
                    ob.fine_accumulation();
                    System.out.println("Enter the name of the person whose TOTAL FINE you want to check");
                    String nper1=br.readLine();
                    for(int i=0;i<top;i++)
                    {
                      if(stack_name[i]==nper1)
                      {
                         System.out.println("TOTAL FINE :-"+stack_fine[i]); 
                      }
                    }
                    break;
                    case 4:
                    System.out.println("Enter day");
                    String day=br.readLine();
                    ob.timings(day);
                    break;
                    case 5:
                    ob.seats_avail();
                    break;
                    case 6:
                    case 7:
                    ob.books();
                    break;
                    case 8:
                    ob.display();
                    break;
                    case 9:
                    System.out.println("Exit");
                }
            }while(ch!=9);
        }
        
        else if(c==2)
        {
            count_seats++;
            System.out.println("TOTAL SEATS PRESENT :-"+(120-count_seats));
        }
    }

    public void push(String s, long roll_no,String nm,int da)
    {
        if(top >=10000)
        {
            System.out.println("PORTAL IS FILLED");
        }
        else
        {
            stack_name[++top]=s;
            stack_rollno[++top]=roll_no;
            queue_nmbk[++rear]=nm;
            stack_date[++top]=da;
        }
    }

    public void details()throws IOException//To enter the details about the user
    {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter the name & rollno");
        String s=br.readLine();
        long r=Long.parseLong(br.readLine());
        System.out.println("Enter the name of book issused and enter the date in the form ddmmyy");
        String nb=br.readLine();
        int de=Integer.parseInt(br.readLine());
        int check=0;
        for(int k=0;k<top;k++)
        {
            if(s==stack_name[k])
            {
                
                if(check<=7)
                {
                    System.out.println("BOOKS CAN BE ISSUSED");
                }
                else
                {
                    System.out.println("BOOKS CAN'T BE ISSUSED");
                    break;
                }
            }
        }
        push(s,r,nb,de);
    }
    public void history()//To get the record of books that are stored.
    {
        
        
    }

    public void due_date()// the date to return the book
    {
        for(int i=top;i>=0;i--)
        {
            int d=stack_date[i]/10000;
            d=d+15;
            int m=stack_date[i]/100;
            int y=stack_date[i]%100;
            String fd="";
            if(m==1||m==3||m==5||m==7||m==8||m==10||m==12)
            {
                if(d>31)
                {
                    d=d-31;
                    m=m+1;
                    if(m>12)
                    {
                        y++;
                    }
                }
                fd=d+""+m+""+y;
            }
            else if(m==4||m==6||m==9||m==11)
            {
                if(d>30)
                {
                    d=d-30;
                    m=m+1;
                    if(m>12)
                    {
                        y++;
                    }
                }
                fd=d+""+m+""+y; 
            }
            else
            {
                if(y%4==0)
                {
                    d=d-29;
                }
                else
                {
                    d=d-28;
                }
                fd=d+""+m+""+y; 
            }

            stack_dueDate[i]=Integer.parseInt(fd);
        }
    }

    public void fine_accumulation()// the total amount of fine the user has to pay
    {
        for(int j=top;j>=0;j--)
        {
            int d1=stack_date[j];
            int d2=stack_dueDate[j];
            int y1=d1%100;
            int m1=d1/10000;
            int d11=d1/100;
            int y2=d2%100;
            int m2=d2/10000;
            int d22=d2/100;
            int df=0;
            if(d11!=d22&&m1==m2&&y1==y2)
            {
                df=d22-d11;
            }
            else if(d11!=d22&&m1!=m2&&y1==y2)
            {
                if(m1==1||m1==3||m1==5||m1==7||m1==8||m1==10||m1==12)
                {
                    df=(31-d1);
                    for(int i=m1+1;i<=m2-1;i++)
                    {
                        if(i==2)
                        {
                            df=df+28;
                        }
                        else if(i==1||i==3||i==5||i==7||i==8||i==10||i==12)
                        {
                            df=df+31;
                        }
                        else
                        {
                            df=df+30;
                        }

                    }
                }
                else if(m1==4||m1==6||m1==9||m1==11)
                {
                    df=(30-d1);
                    for(int i=m1+1;i<=m2-1;i++)
                    {
                        if(i==2)
                        {
                            df=df+28;
                        }
                        else if(i==1||i==3||i==5||i==7||i==8||i==10||i==12)
                        {
                            df=df+31;
                        }
                        else
                        {
                            df=df+30;
                        }

                    }
                }
                else
                {
                    df=(28-d1);
                    for(int i=m1+1;i<=m2-1;i++)
                    {
                        if(i==2)
                        {
                            df=df+28;
                        }
                        else if(i==1||i==3||i==5||i==7||i==8||i==10||i==12)
                        {
                            df=df+31;
                        }
                        else
                        {
                            df=df+30;
                        }

                    } 
                }
                df=df+d2;
            }
            else if((d1==d2||d1!=d2)&&(m1==m2||m1!=m2)&&y1!=y2)
            {
                for(int i=y1;i<=y2;i++)
                {
                    if(m1==m2)
                    {
                        if(d1==d2)
                        {
                            df=df+365;
                        }
                        else
                        {
                            df=df+(365-d1-d2);
                        }
                    }
                    else
                    {
                        df=df+(365-((m1-m2)*30+(d1-d2)));
                    }
                }
            }
            else
            {
                df=0;
            }
            stack_fine[j]=df*2;
        }
    }

    public void timings(String wd)
    {
        String w=wd;
        if(w=="Monday"||w=="Tuesday"||w=="Wednesday"||w=="Thrusday"||w=="Friday")
        {
            System.out.println("8:30am-8:30pm");
        }
        else if(w=="")
        {
            System.out.println("8:30am-5:30pm");
        }
        else
        {
            System.out.println("CLOSED");
        }
    }

    public  void seats_avail()
    {
        if(count_seats>90)
        {
            System.out.println("SEATS ARE FILLED");
        }
        else
        {
            System.out.println("SEATS ARE FREE...");
        }
    }

    public void books()throws IOException
    {
        System.out.println("1.TO KNOW LIST OF BOOKS PRESENT \n 2.TO ADD A NEW BOOK");
        int h=Integer.parseInt(br.readLine());

        switch(h)
        {
            case 1:
            System.out.print("\nList of Books :- ");
            if (rear == 0&& front==0)
            {
                System.out.print("Empty\n");
                return ;
            }
            else
            {
                for (int i = front; i <= rear; i++)
                {
                    System.out.print(queue_nmbk[i]+" ");
                    size++;
                }
            }
            System.out.println(); 
            break;
            case 2:
            System.out.println("Enter the name of the book you want to add");
            String p=br.readLine();
            if (rear == -1) 
            {
                front = 0;
                rear = 0;
                queue_nmbk[rear] =p;
            }
            else if (rear + 1 >= size)
            {
                throw new IndexOutOfBoundsException("Overflow Exception");
            }
            else if ( rear + 1 < size)
            {
                queue_nmbk[++rear] = p;
            }
            break;
            default:
            System.out.println("WRONG CHOICE");
        }
    }
    public void display()
    {
        System.out.println("Names of member \t RollNo");
        for(int i=0;i<top;i++)
        {
            System.out.println(stack_name[i]+"\t"+stack_rollno[i]);
        }
    }
}