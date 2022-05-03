package game;

import java.util.Arrays;

public class Player {
	
	private String name;
    

	public Player(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	public boolean turn() {
		
		int[] arr = new int[3];

		for(int i = 0; i < 3; i++) {
			arr[i] = (int)(Math.random() * 6) + 1;
		}
		
		System.out.println("===" + name + "님의 주사위 결과 ===");
		System.out.print(Arrays.toString(arr));                     //   for(int i = 0; i < 3; i++)
			
			if(arr[0] == arr[1] && arr[1] == arr[2]) {
				System.out.println(" 성공!");
				return true;
			} else {
				System.out.println(" 실패!");
			    return false;
			}
		}
}
/*

비교 대상이 많을 경우
boolean res = true;
    for (int i = 1; i < arr.length; i++) {
        if(arr[0] != arr[i]) {
	    res = false;
	    break;
        }
}
    if(res)
    	system.out.println("성공");
    else
    	system.out.println("실패");

		}
		
*/