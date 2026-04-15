public class GroupsLinking {
	static Scanner scanner = new Scanner(System.in);
	static int[][] groupsLinking = { { 3, 1, 2, 3 }, { 3, 4, 5, 6 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 1, 7, 0, 0 },
			{ 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 } };
	static List<Integer> freeList = new ArrayList<Integer>();
	static {
		for (int i = 1; i <= 7; i++) {
			freeList.add(i);
		}
		;
	};


	public static void allocate() {
		
		int freeNum, allocativeNum;
	
		if (groupsLinking[0][0] > 1) {
			freeNum = groupsLinking[0][0];
			allocativeNum = groupsLinking[0][freeNum];
			groupsLinking[0][freeNum] = 0;
			groupsLinking[0][0]--;
			freeList.remove((Integer) allocativeNum);
			System.out.println("分配的盘块号为：" + allocativeNum);
		}

		else if (groupsLinking[0][0] == 1) {
				allocativeNum = groupsLinking[0][1];
				for (int j = 0; j < groupsLinking[allocativeNum].length; j++) {
					
					groupsLinking[0][j] = groupsLinking[allocativeNum][j];
					groupsLinking[allocativeNum][j] = 0;
				}

				freeList.remove((Integer) allocativeNum);
				System.out.println("分配的盘块号为：" + allocativeNum);
		} else {
			System.out.println("已经没有空闲块了");
        }
		display();
	}


	public static void recycling() {

		int freeNum = 0;
		System.out.println("请输入你想回收的空闲盘块的盘块号:");

		int recyclingNum = scanner.nextInt();
		for (int i = 0; i < freeList.size(); i++) {
			if (freeList.get(i) == recyclingNum) {
				System.out.println("该空闲块已经存在！");
				return;
			}
		}

		if (groupsLinking[0][0] < 3) {
			freeNum = groupsLinking[0][0] + 1;
			groupsLinking[0][freeNum] = recyclingNum;
			freeList.add(recyclingNum);
			groupsLinking[0][0]++;
		}

		else {
			for (int j = 0; j <= 3; j++)
				groupsLinking[recyclingNum][j] = groupsLinking[0][j];
				groupsLinking[0][0] = 1;
				groupsLinking[0][1] = recyclingNum;
				freeList.add(recyclingNum);
		}
		display();
	}
	public static void display() {
		int freeNum, temp, groupNum = 1;
		if (groupsLinking[0][0] != 0) {
			freeNum = groupsLinking[0][0];
			System.out.println("第1组盘块:");
			for (int j = 1; j <= freeNum; j++) {
				System.out.print(groupsLinking[0][j] + " ");
			}
			System.out.println();
			temp = groupsLinking[0][1];
			groupNum++;
			while (groupsLinking[temp][0] != 0) {
				System.out.println("第" + groupNum + "组盘块:");
	
				freeNum = groupsLinking[temp][0];
				for (int j = 1; j <= freeNum; j++) {
					System.out.print(groupsLinking[temp][j] + " ");
				}
				System.out.println();
				groupNum++;
				temp = groupsLinking[temp][1];
			}
		}
		else {
			System.out.println("空闲盘块已经全部被分配!");
		}
	}


	public static void menu() {
		System.out.println("=========成组链接法=========");
		System.out.println("请输入操作：1:分配，2:回收,3:退出");
		String flag = scanner.next();
	
		if (flag.equals("1")) {
			allocate();
			menu();
		}
		else if (flag.equals("2")) {
			recycling();
			menu();
		}
		else {
			System.out.println("已退出");
			scanner.close();
			return;
		}
	}

	public static void main(String[] args) {
		System.out.println("=========成组链接法初始化=========");
		display();
		menu();
	}
}
