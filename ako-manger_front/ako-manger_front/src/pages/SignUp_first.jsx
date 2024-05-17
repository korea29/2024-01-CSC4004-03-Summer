import React, { useState } from 'react';
import Ako_sit from '../pages/assets/ako-sit.png';
import '../css/SignUp_first.css';
import { useNavigate } from 'react-router-dom';
// import HeaderComponent from'../pages/components/HeaderComponent';

const SignUp_first = () => {
    const navigate = useNavigate();

    const gotoSignUpScreen = () => {
        navigate("/signup");
    };
    const gotoLoginScreen = () => {
      navigate("/login");
  };

    const [userId, setUserId] = useState('');
    const [password, setPassword] = useState('');
    const [confirmPassword, setConfirmPassword] = useState('');
    const [isSignUpSuccess, setIsSignUpSuccess] = useState(false);

    const handleSubmit = async (e) => {
        e.preventDefault();

        // 비밀번호와 비밀번호 확인이 일치하는지 확인
        if (password !== confirmPassword) {
            alert('비밀번호와 비밀번호 확인이 일치하지 않습니다.');
            return;
        }

        // 더미 데이터를 사용하여 회원가입 시뮬레이션
        try {
            // API 호출 시뮬레이션을 위한 지연 시간 추가
            await new Promise((resolve) => setTimeout(resolve, 1000));

            // 회원가입 성공 시뮬레이션
            const response = { ok: true };

            if (response.ok) {
                setIsSignUpSuccess(true);
            } else {
                console.error('회원가입 요청 실패');
                alert('회원가입 중 오류가 발생했습니다! 다시 시도해주세요.');
            }
        } catch (error) {
            console.error('회원가입 요청 중 오류 발생:', error);
            alert('회원가입 중 오류가 발생했습니다. 다시 시도해주세요.');
        }
    };

    return (
        <div className="signup-container">
            {/* <HeaderComponent/> */}
            <img className="home-image" src={Ako_sit} alt="LoginScreen" />
            {isSignUpSuccess && (
                <div className="signup-success-overlay">
                    <div className="signup-success-message" >
                        <p>회원가입 성공! 추가 정보를 입력하러 가시겠습니까?</p>
                        <button className="add-info-button" onClick={gotoLoginScreen}>
                            No
                        </button>
                        <button className="add-info-button" onClick={gotoSignUpScreen}>
                            Yes!
                        </button>
                    </div>
                </div>
            )}
            {!isSignUpSuccess && (
                <form onSubmit={handleSubmit} className="signup-form">
                    <input
                        type="text"
                        placeholder="아이디"
                        value={userId}
                        onChange={(e) => setUserId(e.target.value)}
                        className="input-field"
                    />
                    <input
                        type="password"
                        placeholder="비밀번호"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        className="input-field"
                    />
                    <input
                        type="password"
                        placeholder="비밀번호 확인"
                        value={confirmPassword}
                        onChange={(e) => setConfirmPassword(e.target.value)}
                        className="input-field"
                    />
                    <button type="submit" className="signup-button">회원가입</button>
                </form>
            )}
        </div>
    );
};

export default SignUp_first;
