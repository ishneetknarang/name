import java.io.*;
class library
{
    String stack_name[]= new String[10000];//stores the name
    long stack_rollno[]=new long[10000];//stores the roll_no
    int stack_date[]=new int[10000];//stores the date of book issuse
    int stack_dueDate[]=new int[10000];//stores the due date of book
    String queue_nmbk[]=new String[10000];//stores the name of book
    int stack_fine[]=new int[10000];//store the total fine
    static int count_seats;//to count no of seats filled;
    public int top=-1;
    InputStreamReader isr= new InputStreamReader(System.in);
    BufferedReader br = new BufferedReader(isr);
    public static void main(String args[])throws IOException
    {
        InputStreamReader isr= new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        System.out.println("WELCOME TO LIBRARY PORTAL");
        System.out.println("1.Lending Section \n 2.Reference Section");
        int c=Integer.parseInt(br.readLine());
        if(c==1)
        {
            System.out.println("1.ENTER DETAILS \n 2.DUE DATE OF BOOK \n 3. FINE ACCUMULATION \n 4. TIMINGS OF LIBRARY \n 5. SEATS AVAILABLE \n 6.List of books \n 7.Add new Books \n 8.Dispaly the members");
            int ch=Integer.parseInt(br.readLine());
        }
        else if(c==2)
        {
            count_seats++;
        }
    }

    public void details(String s,long roll_no)throws IOException//To enter the details about the user
    {
        if(top >=10000)
        {
            System.out.println("PORTAL IS FILLED");
        }
        else
        {
            stack_name[++top]=s;
            stack_rollno[++top]=roll_no;
            System.out.println("Enter the name of book issused and enter the date in the form ddmmyy");
            queue_nmbk[++top]=br.readLine();
            stack_date[++top]=Integer.parseInt(br.readLine());
        }
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
}