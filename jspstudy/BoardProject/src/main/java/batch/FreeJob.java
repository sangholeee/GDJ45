package batch;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import domain.Free;
import repository.FreeDAO;

public class FreeJob implements Job {

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {

		try {

			List<Free> board = FreeDAO.getInstance().selectBoardTopHit();
		
		
			File file = new File("C:\\GDJ45\\jspstudy\\BoardProject\\top.txt");
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			for(int i = 0, length = board.size(); i < length; i++) {
				Free writer = board.get(i);
				bw.write("게시글번호 " + writer.getFreeNo() + "\n");
				bw.write("작성자 " + writer.getWriter() + "\n");
				bw.write("제목 " + writer.getTitle() + "\n");
				bw.write("작성IP " + writer.getIp() + "\n");
				bw.write("조회수 " + writer.getHit() + "\n");
				bw.write("내용 \n" + writer.getContent());
				
			} 
			bw.close();
		
			
			System.out.println("top.txt 파일이 생성되었습니다.");
			} catch (Exception e) {
			e.printStackTrace(); 
		}
		
	}

}
