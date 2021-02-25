package People;

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
        System.out.println("欢迎使用吃货联盟点餐系统!");
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
                    for(int i=0;i<names.length;i++){
                        if(names[i]==null){
                            isadd=true;//订餐未满,可以订餐
                            System.out.print("请输入订餐人姓名:");
                            String name=scan.nextLine();
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
                            while(time<20||time>10){
                                System.out.println("该时间段本店不进行配送,请重新选择配送时间");
                                time=scan.nextInt();
                            }
                            //送餐地址
                            System.out.println("请输入送餐地址:");
                            String adress=scan.nextLine();
                            //输出订餐信息给用户看,并把订餐信息添加到订餐信息
                            System.out.println("订餐成功");
                            //菜品信息: 菜名名称 份数
                            String disInfo=disNames[no-1]+" "+number+"份";//*******注意:用户选择的菜品下标比真正的下表位置大1
                            System.out.println("您订的是"+disInfo);
                            System.out.println("送餐时间是:"+time+"点");
                            //餐费 配送费 总计
                            //餐费 prices 数组下标比用户选择的菜品编号小1
                            double disPrice=price[no-1];//单价 * 份数
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
                    System.out.println("序号\t订餐人\t订餐菜品\t\t配送时间\t配送地址\t订单金额\t订单状态");
                    for (int i = 0; i < names.length; i++) {
                        if (names[i] != null) {
                            System.out.println((i + 1) + names[i] + "\t" + dishMsg[i] + "\t" + times[i] + "\t" + adresses[i] + "\t" + sumPrices[i] + "\t" + states[i]);
                        }
                    }
                    break;
                case 3:
                    System.out.println("--------------签收订单--------------");
                    break;
                case 4:
                    System.out.println("--------------删除订单--------------");
                    break;
                case 5:
                    System.out.println("--------------我要点赞---------------");
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
