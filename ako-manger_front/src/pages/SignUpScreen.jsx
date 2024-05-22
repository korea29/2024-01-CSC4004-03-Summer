import React, { useState,useRef } from 'react';
import '../css/SignUpScreen.css';
import profileImage from '../pages/assets/ako-profile.png';
import HeaderComponent from './components/HeaderComponent';

const SignUpScreen = () => {
  
  const [formData, setFormData] = useState({
    university: '',
    studentId: '',
    name: '',
    major: '',
    college: '',
    secondMajor: '',
    profileImage: null,
  });

  const inputFileRef = useRef(null); // Ref for the file input element

  const handleFileInputChange = (e) => {
    setFormData({ ...formData, profileImage: e.target.files[0] }); // 사진 업로드
  };
  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };
  const [showUploadText, setShowUploadText] = useState(false);

  const handleSubmit = (e) => {
    e.preventDefault();
    // 여기에 회원가입 처리 로직 추가
    console.log(formData);
  };

  // 유효성 검사 함수
  const isValid = () => {
    return (
      formData.university.trim() !== '' &&
      formData.studentId.trim() !== '' &&
      formData.name.trim() !== '' &&
      formData.major.trim() !== '' &&
      formData.college.trim() !== ''
    );
  };

  return (
    <div className="signup-container">
      {/* Top bar */}
      <div className="top-bar">
        <img src={profileImage} alt="AKO Logo" className="ako-logo" />
        <div className="top-bar-text">A-ko-Mentor</div>
      </div>
      <div className="profile-container">
      <div
          className="profile-image-container"
          onMouseEnter={() => setShowUploadText(true)}
          onMouseLeave={() => setShowUploadText(false)}
          onClick={() => inputFileRef.current.click()}
        >
          {showUploadText && <div className="upload-text-overlay">사진업로드하기</div>}
          {formData.profileImage ? (
            <img src={URL.createObjectURL(formData.profileImage)} alt="Profile" className="profile-image" />
          ) : (
            <img src={profileImage} alt="Profile" className="profile-image" />
          )}
        </div>
  <div className="profile-text" onClick={() => inputFileRef.current.click()}>프로필 설정 +</div>
  <input
    type="file"
    accept="image/*"
    ref={inputFileRef}
    style={{ display: 'none' }}
    onChange={handleFileInputChange}
  />
</div>

      <div className="required-text">*필수입력</div>
      <form className="signup-form" onSubmit={handleSubmit}>
        <input
          className="form-input"
          type="text"
          name="university"
          placeholder="소속 대학교*"
          value={formData.university}
          onChange={handleChange}
        />
        <input
          className="form-input"
          type="text"
          name="studentId"
          placeholder="학번*"
          value={formData.studentId}
          onChange={handleChange}
        />
        <input
          className="form-input"
          type="text"
          name="name"
          placeholder="이름*"
          value={formData.name}
          onChange={handleChange}
        />
        <select
          className="select-input" // 드롭다운 메뉴 스타일링을 위해 클래스 추가
          name="college"
          value={formData.college}
          onChange={handleChange}
        >
          <option value="">소속 단과대학 선택*</option>
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
          {/* 필요한 만큼 옵션을 추가 */}
        </select>
        <input
          className="form-input"
          type="text"
          name="major"
          placeholder="전공*"
          value={formData.major}
          onChange={handleChange}
        />
        <input
          className="form-input"
          type="text"
          name="secondMajor"
          placeholder="부전공"
          value={formData.secondMajor}
          onChange={handleChange}
        />
        <button className="submit-button" type="submit" disabled={!isValid()}>
          입력하기
        </button>
      </form>
    </div>
  );
};

export default SignUpScreen;
