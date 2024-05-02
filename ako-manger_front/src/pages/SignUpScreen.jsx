import React, { useState } from 'react';
import '../css/SignUpScreen.css';
import profileImage from '../pages/assets/ako-profile.png';


const SignUpScreen = () => {
  const [formData, setFormData] = useState({
    university: '',
    studentId: '',
    name: '',
    major: '',
    college: '',
    secondMajor: '',
  });

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    // 여기에 회원가입 처리 로직 추가
    console.log(formData);
  };

  return (
    <div className="signup-container">
      {/* Top bar */}
      <div className="top-bar">
        <img src={profileImage} alt="AKO Logo" className="ako-logo" />
        <div className="top-bar-text">A-ko-Mentor</div>
      </div>
      <div className="profile-container">
      <div className="profile-image-container">
          <img src={profileImage} alt="Profile" className="profile-image" />
        </div>
        <div className="profile-text">프로필 설정</div>
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
          name="college"
          placeholder="소속 단과대학*"
          value={formData.college}
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
        <button className="submit-button" type="submit">
          회원가입
        </button>
      </form>
    </div>
  );
};

export default SignUpScreen;