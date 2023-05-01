package com.jun.DowooriVer2.service;

import com.jun.DowooriVer2.DTO.CalendarDTO;
import com.jun.DowooriVer2.DTO.ChartDTO;
import com.jun.DowooriVer2.DTO.ChartTeamDTO;
import com.jun.DowooriVer2.DTO.DayoffTeamDTO;
import com.jun.DowooriVer2.domain.Board;
import com.jun.DowooriVer2.domain.Department;
import com.jun.DowooriVer2.repository.BoardRepository;
import com.jun.DowooriVer2.repository.DeptRepository;
import com.jun.DowooriVer2.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
@Slf4j
public class BoardService {

    private BoardRepository boardRepository;
    private DeptRepository deptRepository;

    public BoardService(BoardRepository boardRepository, DeptRepository deptRepository) {
        this.boardRepository = boardRepository;
        this.deptRepository = deptRepository;
    }

    public List<Board> findAll(Long empNum){
        return boardRepository.findAll(empNum);
    }

    public void createBoard(Board board) {
        boardRepository.createBoard(board);
    }

    public void deleteBoard(Long deptNum) {
        boardRepository.deleteBoard(deptNum);
    }

    public Optional<Board> findById(Long boardId){
        return boardRepository.findById(boardId);
    }

    public void updateBoard(Board board) {
        boardRepository.updateBoard(board);
    }

    public List<CalendarDTO> findAllByDept(Long deptNum) {
        return boardRepository.findAllByDept(deptNum);}

    public int findAllCnt(Long empNum) {
        return boardRepository.findAllCnt(empNum);
    }

    public List<Board> findAllPaging(Long empNum, int startIndex, int pageSize) {
        return boardRepository.findAllPaging(empNum, startIndex, pageSize);
    }


    public List<ChartDTO> dayoffCnt(Long empNum, Long deptNum) {

        return boardRepository.dayoffCnt(empNum, deptNum);

    }

    public List<ChartTeamDTO> teamCnt(Long empNum, Long deptNum) {
        return boardRepository.teamCnt(empNum, deptNum);
    }

    public List totalDayoff(String deptNum) {

        // client 단에 보내줄 list
        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();

        // list 안에 담아줄 각 Map 정의
        Map resultMap = new HashMap();

        List<String> monthsList = new ArrayList<String>();
        List<Integer> totalCntList = new ArrayList<Integer>();

        for (int i = 0; i < 12; i++) {

            if(i + 1 < 10) {
                monthsList.add("0" + (i + 1) + "월");
            }else{
                monthsList.add(( i + 1 ) + "월");
            }
            totalCntList.add(0);

        }

        resultMap.put("months", monthsList);
        resultMap.put("totalCnt", totalCntList);

        List<DayoffTeamDTO> dayoffTeamDTOS = boardRepository.totalDayoffCnt(deptNum);

        for (int i = 0; i < dayoffTeamDTOS.size(); i++) {

            String months1 = dayoffTeamDTOS.get(i).getMonths();
            String teamName = dayoffTeamDTOS.get(0).getTeamName();
            Long totalCnt1 = dayoffTeamDTOS.get(i).getTotalCnt();

            log.info("months1 = " + months1);
            log.info("totalCnt1 = " + totalCnt1);
            log.info("teamName = " + teamName);


            List months = (List) resultMap.get("months");

            // indexOf : 해당 value 가 있을 경우 index return, 없을 경우 -1 return
            int indexOfMonth = months.indexOf(months1);

            List totalCnt = (List) resultMap.get("totalCnt");



            totalCnt.set(indexOfMonth, Math.toIntExact(totalCnt1));

            resultMap.put("deptName", teamName);

        }

        resultList.add(resultMap);

        return resultList;
    }


    public List allDeptTotalDayoff() {

        // client 단에 보내줄 list
        List resultList = new ArrayList();

        List<Department> departments = deptRepository.selectAllDept();

        System.out.println("departments.toString() = " + departments.toString());

        for (int i = 0; i < departments.size(); i++) {
            List allDeptList = totalDayoff(String.valueOf(departments.get(i).getId()));
            resultList.add(allDeptList);
        }
        return resultList;

    };

}
