import React, { useState, useRef, useEffect } from "react";
import "../css/SignUpScreen.css";
import profileImage from "../pages/assets/ako-profile.png";
import HeaderComponent from "./components/HeaderComponent";
import { useNavigate, useLocation } from "react-router-dom";

const SignUpScreen = () => {
  const navigate = useNavigate();
  const location = useLocation();
  const { userId, password } = location.state || {};

  const [isSubmitted, setIsSubmitted] = useState(false); // 정보 입력 성공 오버레이
  const [showUploadText, setShowUploadText] = useState(false);
  const [selectedFileName, setSelectedFileName] =
    useState("수강과목 첨부(.xls)*"); // 선택된 파일명
  const [messageVisible, setMessageVisible] = useState(false);

  const handleMouseEnter = () => {
    setMessageVisible(true);
  };

  const handleMouseLeave = () => {
    setMessageVisible(false);
  };

  const [formData, setFormData] = useState({
    university: "",
    studentId: userId || "",
    name: "",
    major: "",
    college: "",
    secondMajor: "",
    birth: "",
    profileImage: null,
    excelFile: null,
    password: password || "",
  });

  const inputFileRef = useRef(null); // 엑셀
  const inputFileRef_2 = useRef(null); // 프로필

  const handleFileInputChange = (e) => {
    // 프로필
    setFormData({ ...formData, profileImage: e.target.files[0] }); // 사진 업로드
  };
  const handleExcelInputChange = (e) => {
    // 엑셀 파일
    const selectedFile = e.target.files[0];
    setFormData({ ...formData, excelFile: e.target.files[0] });
    setSelectedFileName(selectedFile.name); // 선택된 파일명 설정
  };
  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };
  const handleStudentIdChange = (e) => {
    // 학번
    const value = e.target.value;
    if (/^\d*$/.test(value) || value === "") {
      // 숫자 형식 또는 빈 문자열인 경우에만 입력을 허용
      setFormData({ ...formData, studentId: value });
    }
  };

  const handleNameChange = (e) => {
    // 이름
    const value = e.target.value;
    if (/^[a-zA-Z가-힣\s]*$/.test(value) || value === "") {
      // 영문자 및 한글 형식 또는 빈 문자열인 경우에만 입력을 허용
      setFormData({ ...formData, name: value });
    }
  };

  const handleMajorChange = (e) => {
    // 전공
    const value = e.target.value;
    if (/^[a-zA-Z가-힣\s]*$/.test(value) || value === "") {
      // 영문자 및 한글 형식 또는 빈 문자열인 경우에만 입력을 허용
      setFormData({ ...formData, major: value });
    }
  };

  const handleSecondMajorChange = (e) => {
    //부전공
    const value = e.target.value;
    if (/^[a-zA-Z가-힣\s]*$/.test(value) || value === "") {
      // 영문자 및 한글 형식 또는 빈 문자열인 경우에만 입력을 허용
      setFormData({ ...formData, secondMajor: value });
    }
  };

  const handleStudentBirthChange = (e) => {
    // 생년월일
    const value = e.target.value;

    // 숫자와 하이픈만 허용하는 정규식을 사용하여 입력 값을 검사합니다.
    if (/^[\d-]*$/.test(value)) {
      setFormData({ ...formData, birth: value });
    }
  };

  const [errorMessage, setErrorMessage] = useState("");

  const handleSubmit = async (e) => {
    e.preventDefault();

    // 필수 입력 사항이 모두 입력되었는지 확인
    if (
      !formData.university ||
      !formData.studentId ||
      !formData.name ||
      !formData.major ||
      !formData.college
    ) {
      // 생년월일 필수로 수정하면 추가해주기
      alert(`모든 필수 입력란을 작성해주세요.`);
      return;
    }

    try {
      // POST 요청을 보낼 서버의 엔드포인트 설정
      const response = await fetch("http://localhost:8080/User/add", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(formData),
      });

      if (response.ok) {
        setIsSubmitted(true);
        setTimeout(() => {
          navigate("/");
        }, 2000); // 2초 후에 홈 화면으로 이동
      } else {
        // 서버로부터 오류 응답을 받은 경우 처리
        const errorData = await response.json();
        alert(`정보 입력 중 오류가 발생했습니다`);
      }
    } catch (error) {
      // 네트워크 오류 등으로 인한 요청 실패 시 처리
      alert(`정보 입력 중 오류가 발생했습니다`);
    }
  };

  return (
    <div className="signup-container">
      {/* <HeaderComponent /> */}
      <div className="profile-container">
        <div
          className="profile-image-container"
          onMouseEnter={() => setShowUploadText(true)}
          onMouseLeave={() => setShowUploadText(false)}
          onClick={() => inputFileRef_2.current.click()}
        >
          {showUploadText && (
            <div className="upload-text-overlay">사진업로드하기</div>
          )}
          {formData.profileImage ? (
            <img
              src={URL.createObjectURL(formData.profileImage)}
              alt="Profile"
              className="profile-image"
            />
          ) : (
            <img src={profileImage} alt="Profile" className="profile-image" />
          )}
        </div>
        <div
          className="profile-text"
          onClick={() => inputFileRef_2.current.click()}
        >
          Profile
        </div>
        <input
          type="file"
          accept=".png,.jpg"
          ref={inputFileRef_2}
          style={{ display: "none" }}
          onChange={handleFileInputChange}
        />
      </div>
      <div className="required-text">*필수입력</div>
      <form className="signup-form" onSubmit={handleSubmit}>
        {errorMessage && <div className="error-message">{errorMessage}</div>}
        {isSubmitted && (
          <div className="signup-success-overlay">
            <div className="signup-success-message">
              <p>정보 입력 성공!</p>
              <p>
                <br />
                홈으로 이동...
              </p>
            </div>
          </div>
        )}

        <select
          className="select-input"
          name="university"
          value={formData.university}
          onChange={handleChange}
        >
          <option value="">소속 대학교 *</option>
          <option value="동국대학교 서울캠퍼스">동국대학교 서울캠퍼스</option>
        </select>
        <input
          className="form-input"
          type="text"
          name="studentId"
          placeholder="학번*"
          value={formData.studentId}
          onChange={handleStudentIdChange}
        />
        <input
          className="form-input"
          type="text"
          name="name"
          placeholder="이름*"
          value={formData.name}
          onChange={handleNameChange}
        />
        <select
          className="select-input"
          name="college"
          value={formData.college}
          onChange={handleChange}
        >
          <option value="">소속 단과대학 *</option>
          <option value="불교대학">불교대학</option>
          <option value="문과대학">문과대학</option>
          <option value="이과대학">이과대학</option>
          <option value="법과대학">법과대학</option>
          <option value="사회과학대학">사회과학대학</option>
          <option value="경찰사법대학">경찰사법대학</option>
          <option value="경영대학">경영대학</option>
          <option value="바이오시스템대학">바이오시스템대학</option>
          <option value="공과대학">공과대학</option>
          <option value="AI융합대학">AI융합대학</option>
          <option value="사범대학">사범대학</option>
          <option value="예술대학">예술대학</option>
        </select>
        <input
          className="form-input"
          type="text"
          name="major"
          placeholder="전공*"
          value={formData.major}
          onChange={handleMajorChange}
        />
        <input
          className="form-input"
          type="text"
          name="secondMajor"
          placeholder="부전공"
          value={formData.secondMajor}
          onChange={handleSecondMajorChange}
        />
        <input
          className="form-input"
          type="text"
          name="birth"
          placeholder="생년월일(YYYY-MM-DD)"
          value={formData.birth}
          onChange={handleStudentBirthChange}
        />
        <div className="file-upload-container">
          {" "}
          {/* 수강과목 첨부 */}
          {selectedFileName && (
            <div className="file-upload-message">
              {selectedFileName}
              <button
                className="attach-file-button"
                onClick={(e) => {
                  e.preventDefault();
                  inputFileRef.current.click();
                }}
              >
                첨부하기
              </button>
              <input
                type="file"
                accept=".xls,.xlsx"
                ref={inputFileRef}
                style={{ display: "none" }}
                onChange={handleExcelInputChange}
              />
            </div>
          )}
        </div>
        <div style={{ position: "relative" }}>
          <div
            className="help"
            onMouseEnter={handleMouseEnter}
            onMouseLeave={handleMouseLeave}
            style={{ position: "absolute", top: "0", left: "0" }}
          >
            도움말{" "}
            {messageVisible && (
              <p
                className="helptext"
                style={{
                  position: "absolute",
                  top: "0",
                  left: "100%",
                  marginLeft: "10px",
                }}
              >
                {" "}
                'mdrims - 졸업 대상자 관리 - 취득학점확인서 조회'에서 .xls
                다운로드
              </p>
            )}
          </div>
        </div>
        <button className="submit-button" type="submit">
          입력하기
        </button>
      </form>
    </div>
  );
};

export default SignUpScreen;
