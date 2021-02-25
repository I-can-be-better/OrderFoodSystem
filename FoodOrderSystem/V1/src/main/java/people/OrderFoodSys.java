package people;

import java.util.Scanner;

public class OrderFoodSys {
    public static void main(String[] args) {
        //定义数据主体:菜品
        String[] disNames = {"红烧鱼", "蔬菜", "牛肉"};//菜品

        double[] price = {38, 10, 50};//价格
        int[] praiseNums = new int[3];//点赞数
        //定义数据主体:订单 订单人 餐品信息 送餐时间 送餐地址 总金额 订单状态
        String[] names = new String[4];//订餐人
        String[] dishMsg = new String[4];//餐品信息
        int[] times = new int[4];//送餐时间
        String[] adresses = new String[4];//送餐地址
        double[] sumPrices = new double[4];//总金额
        int[] states = new int[4];//订单状态
        //初始化两个订单信息
        names[0] = "张三";
        dishMsg[0] = "红烧鱼两份";
        times[0] = 10;
        adresses[0] = "天堂";
        sumPrices[0] = 76;//餐费>50,免送餐费,否则6元
        states[0] = 0;

        names[1] = "李四";
        dishMsg[1] = "蔬菜两份";
        times[1] = 6;
        adresses[1] = "地狱";
        sumPrices[1] = 26;
        states[1] = 0;

        //搭建项目的总体框架
        boolean flag = false;//设置退出系统状态false为未退出
        int num = -1;
        /*
        希望用户输入0时的输入数字,num=0重复现实主菜单
        num在用户每次订餐后又操作后进行提示重新复制
         */
        System.out.println("欢迎使用点餐系统!");
        Scanner scan = new Scanner(System.in);
        do {
            System.out.println("1.我要订餐");
            System.out.println("2.查看餐带");
            System.out.println("3.签收订单");
            System.out.println("4.删除订单");
            System.out.println("5.我要点赞");
            System.out.println("6.退出系统");
            System.out.println("请选择服务:");
            int choose = scan.nextInt();
            switch (choose) {
                case 1:
                    System.out.println("--------------我要订餐---------------");
                    //订餐前判断订单是否已满
                    boolean isadd=false;
                    System.out.println("===============可以订餐吗: " + isadd);
                    for(int i=0;i<names.length;i++){
                        if(i<names.length){
                            isadd=true;//订餐未满,可以订餐
                            System.out.print("----------可以订餐吗 : " + isadd);
                            System.out.print("请输入订餐人姓名:");
                            String name=scan.next();
                            System.out.println("===========订餐人姓名 : " + name);
                            //循环输出菜品信息
                            System.out.println("序号\t菜名\t单价");
                            for(int j=0;j<disNames.length;j++){
                                String praise=(praiseNums[j]==0)?"":praiseNums[j]+"赞";
                                System.out.println((j+1)+"\t"+disNames[j]+"\t"+price[j]+"\t"+praise);
                            }
                            //菜品编号的输入及判断
                            System.out.print("请输入你要点菜品");
                            int no=scan.nextInt();
                            while(no<1||no>disNames.length){
                                System.out.println("本店没有这菜品,请重新选择:");
                                no=scan.nextInt();
                            }
                            //点菜份数
                            System.out.print("请输入您需要的份数:");
                            int number=scan.nextInt();
                            //送餐时间的输入及判断
                            System.out.print("请输入送餐时间(送餐时间只能在10-20之间的整点):");
                            int time=scan.nextInt();
                            while(time>20||time<10){
                                System.out.println("该时间段本店不进行配送,请重新选择配送时间");
                                time=scan.nextInt();
                            }
                            //送餐地址
                            System.out.print("请输入送餐地址:");
                            String adress=scan.nextLine();
                            //输出订餐信息给用户看,并把订餐信息添加到订餐信息
                            System.out.println("订餐成功");
                            //菜品信息: 菜名名称 份数
                            String disInfo=disNames[no-1]+" "+number+"份";//*******注意:用户选择的菜品下标比真正的下表位置大1
                            System.out.println("您订的是"+disInfo);
                            System.out.println("送餐时间是:"+time+"点");
                            //餐费 配送费 总计
                            //餐费 prices 数组下标比用户选择的菜品编号小1
                            double disPrice=price[no-1]*number;//单价 * 份数
                            double peisong=(disPrice>50)?0:6;
                            double sumPrice=disPrice+peisong;
                            System.out.println("餐费:"+disPrice+"配送费:"+peisong+"总共:"+sumPrice);

                            //把订单信息添加到订单信息里,插入订单的位置是关键:i
                            names[i]=name;
                            dishMsg[i]=disInfo;
                            times[i]=time;
                            adresses[i]=adress;
                            sumPrices[i]=sumPrice;

                            break;
                        }else{
                            System.out.println("订单已满");//订单已满
                        }
                    }
                    break;
                case 2:
                    System.out.println("--------------查看餐带---------------");
                    System.out.println("序号\t\t订餐人\t\t订餐菜品\t\t配送时间\t\t配送地址\t\t订单金额\t\t订单状态");
                    for (int i = 0; i < names.length; i++) {
                        if (names[i] != null) {
                            System.out.println((i + 1)+"\t\t"+ names[i] + "\t\t" + dishMsg[i] +"\t\t"+ times[i] + "\t\t" + adresses[i] + "\t\t" + sumPrices[i] + "\t\t" + states[i]);
                        }
                    }
                    break;

                case 3:
                    System.out.println("--------------签收订单--------------");
                    //签收之前,要判断订单是否存在,false=不存在,不能签收;true=存在,且预定状态:完成状态
                    boolean isSign=false;//默认为订单的存在状态为存在
                    System.out.print("请输入你要签收的订单编号:");
                    int signNO=scan.nextInt();
                    for(int i=0;i<names.length;i++){
                        if(names[i]!=null&&states[i]!=0&&i==signNO-1){
                            //有订单信息,且订单状态为0,且用户输入的订单编号我能读到
                            //能签收
                            isSign=true;
                            states[i]=1;
                            System.out.println("订单签收成功!");
                        }else if((names[i]!=null&&states[i]==1&&i==signNO-1)){
                            //有订单信息,状态为已完成,能找到
                            //不能签收
                            System.out.println("您选择的订单已经签收,不能再次签收");
                        }else {
                            System.out.println("该订单不存在!");
                        }
                    }
                    break;
                case 4:
                    System.out.println("--------------删除订单--------------");
                    //删除之前,要判断订单是否存在,false=不存在,不能签收;true=存在,且预定状态:完成状态
                    boolean isdelete=false;//默认为订单的存在状态为存在
                    System.out.print("请输入你要删除的订单编号:");
                    int deletenum=scan.nextInt();

                    for(int i=0;i<names.length;i++){
                        if(names[i]!=null&&states[i]==0&&i==deletenum-1){
                            //有订单信息,且订单状态为0,且用户输入的订单编号我能读到
                            //订单状态为预定,不能删除
                            isdelete=true;
                        }else if((names[i]!=null&&states[i]==1&&i==deletenum-1)){
                            //有订单信息,状态为已完成,能找到
                            isdelete=true;
                            //但是订单状态为完成,能删除
                            /**
                             * 找到删除订单的位置下标,把i后面的元素依次往前移动,最后一个数组要置空
                             * 注意:移动的过程其实是一个把后一个元素往前一个元素复制的过程
                             * 最后一个元素一定要置空,方便下新订单
                             */
                            for(int j=i;j<names.length-1;j++){
                                names[j]=names[j+1];
                                dishMsg[j]=dishMsg[j+1];
                                times[j]=times[j+1];
                                adresses[j]=adresses[j+1];
                                sumPrices[j]=sumPrices[j+1];
                                states[j]=states[j+1];
                            }
                            //最后一个元素的置空
                            names[names.length-1]=null;
                            dishMsg[dishMsg.length-1]=null;
                            times[times.length-1]=0;
                            adresses[names.length-1]=null;
                            sumPrices[names.length-1]=0;
                            states[states.length-1]=0;
                            System.out.println("订单已删除!");

                            System.out.println("您选择的订单已经签收,不能再次签收");
                        }else{
                            System.out.println("订单不存在");
                        }
                    }
                    break;
                case 5:
                    System.out.println("--------------我要点赞---------------");
                    //显示菜品信息
                    System.out.println("序号\t菜名\t单价");
                    for(int j=0;j<disNames.length;j++){
                        String praise=(praiseNums[j]==0)?"":praiseNums[j]+"赞";
                        System.out.println((j+1)+"\t"+disNames[j]+"\t"+price[j]+"\t"+praise);
                    }
                    //点赞
                    System.out.print("请输入您要点赞的菜品:");
                    int prisenum=scan.nextInt();
                    while(prisenum<1||prisenum>disNames.length){
                        System.out.print("本店没有该菜品,无法点赞,请重新输入菜品序号:");
                        prisenum=scan.nextInt();
                    }
                    //关键点:把那个位置的菜品点赞数+1,点赞菜品的位置=prisenum-1
                    praiseNums[prisenum-1]++;
                    break;
                case 6:
                    System.out.println("--------------退出系统--------------");
                    flag = true;
                    break;
                default:
                    System.out.println("还没有开发此功能");
                    break;
            }
            System.out.println("输入0返回主菜单");
            num = scan.nextInt();

        } while (num == 0);
        System.out.println("欢迎使用,欢迎下次光临");

    }
}
