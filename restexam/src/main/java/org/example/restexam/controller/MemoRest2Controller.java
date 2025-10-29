package org.example.restexam.controller;

import org.example.restexam.domain.Memo;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
@RestController
@RequestMapping("/api2/memos")
public class MemoRest2Controller {
    private final Map<Long, Memo> memos = new HashMap<>();
    private final AtomicLong counter = new AtomicLong();

    //1. http://localhost:8080/api2/memos    -- GET   (모든 메모를 조회)  -- List<Memo>
    @GetMapping
    public List<Memo>  getMemos() {
        return new ArrayList<>(memos.values());  //가변리스트  //memos.values().stream().toList(); -- 불변리스트
    }

    //2. http://localhost:8080/api2/memos/{id}  -- GET  id에 해당하는 메모 조회   Memo
    @GetMapping("/{id}")
    public Memo getMemo(@PathVariable("id") Long id) {
        return memos.getOrDefault(id, null);
    }
    //3. http://localhost:8080/api2/memos     -- POST    MEMO 생성        --  Long
    @PostMapping
    public Long createMemo(@RequestBody Memo memo) {
        Long id = counter.incrementAndGet();
        memo.setId(id);
        memos.put(id, memo);  //지금은 map 저장하지만,   나중에는 Service를 호출하는 로직이 여기 들어가겠죠?
        return id;
    }


    //4. http://localhost:8080/api2/memos/{id}  --  PUT   메모 수정        --  메시지 String or  수정된 Memo
    @PutMapping("/{id}")
    public String updateMemo(@PathVariable("id") Long id, @RequestBody Memo memo) {
        if(!memos.containsKey(id))
            return "Memo not found";
        memo.setId(id);
        memos.put(memo.getId(), memo);
        return "Memo updated";
    }
    //5. http://localhost:8080/api2/memos/{id}  --   DELETE  메모 삭제     -- String
    @DeleteMapping("/{id}")
    public String deleteMemo(@PathVariable("id") Long id) {
        if(memos.remove(id) == null)
            return "Memo not found";
        return "Memo deleted";
    }

//    위에 제시한 요청이 들어왔을 때 이것을 모두 처리할 수 있는 RestController 를 만들어 주세요.





    //curl test

    //1. curl -X GET http://localhost:8080/api2/memos
    //2. curl -X GET http://localhost:8080/api2/memos/1
    //3. curl -X POST -H "Content-type: application/json" -d "{\"content\":\"첫번째 메모\"}"  http://localhost:8080/api2/memos
    //  맥  curl -X POST -H "Content-type: application/json" -d '{"content":"첫번째 메모"}' http://localhost:8080/api2/memos
    //4. curl -X PUT -H "Content-type: application/json" -d "{\"content\":\"수정된 메모\"}"  http://localhost:8080/api2/memos/1
    // 맥  curl -X PUT -H "Content-type: application/json" -d '{"content":"수정된 메모"}'  http://localhost:8080/api2/memos/1
    //5. curl -X DELETE http://localhost:8080/api2/memos/1


}
