import { useState } from "react";

// BoardItem.jsx (새로 생성, 단위 요소)
const BoardItem = ({
  id,
  title,
  writer,
  created,
  readCount,
  onRemove,
  onEdit,
}) => {
  // 1. 수정 모드 상태 관리
  const [isEditing, setIsEditing] = useState(false);

  // 2. 수정 중인 입력값 관리 (초기값은 props로 받은 기존 값)
  const [localTitle, setLocalTitle] = useState(title);
  const [localWriter, setLocalWriter] = useState(writer);

  // 수정 버튼 클릭 시 실행
  const handleEditClick = () => {
    setIsEditing(true);
  };

  // 취소 버튼 클릭 시 실행
  const handleCancelClick = () => {
    setIsEditing(false);
    // 취소 시 입력값을 원래 props 값으로 되돌림
    setLocalTitle(title);
    setLocalWriter(writer);
  };

  // 저장 버튼 클릭 시 실행
  const handleSaveClick = () => {
    // 부모에게 수정된 데이터 전달
    onEdit(id, { title: localTitle, writer: localWriter });

    // 수정 모드 종료
    setIsEditing(false);
  };

  return (
    <tr>
      <td>{id}</td>

      {/* 제목: 수정 모드면 input, 아니면 텍스트 */}
      <td>
        {isEditing ? (
          <input
            type="text"
            value={localTitle}
            onChange={(e) => setLocalTitle(e.target.value)}
          />
        ) : (
          title
        )}
      </td>

      {/* 작성자: 수정 모드면 input, 아니면 텍스트 */}
      <td>
        {isEditing ? (
          <input
            type="text"
            value={localWriter}
            onChange={(e) => setLocalWriter(e.target.value)}
          />
        ) : (
          writer
        )}
      </td>

      <td>{created}</td>
      <td>{readCount}</td>

      <td style={{ textAlign: "center" }}>
        {isEditing ? (
          // 수정 모드일 때: [저장] [취소]
          <>
            <button onClick={handleSaveClick}>저장</button>
            <button onClick={handleCancelClick}>취소</button>
          </>
        ) : (
          // 일반 모드일 때: [수정] [삭제]
          <>
            <button onClick={handleEditClick}>수정</button>
            <button onClick={() => onRemove(id)}>삭제</button>
          </>
        )}
      </td>
    </tr>
  );
};

export default BoardItem;
