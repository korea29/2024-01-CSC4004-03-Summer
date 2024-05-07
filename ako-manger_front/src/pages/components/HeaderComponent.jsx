import React from "react";
import MenuLogo from "../../Images/Menu _ Logo.png";
import defaultAkoImage from "../../Images/기본형아코얼굴_(designed by 박세리,원혜림).png";
import helpicon from "../../Images/help_outline.png";
import settingsicon from "../../Images/settings.png";
import appsicon from "../../Images/apps.png";
import startchat from "../../Images/startchat.png";
import chaticon from "../assets/chat.png"; 

const HeaderComponent = () => {
  return (
    <div className="header" style={styles.header}>
      {/* 테스트 */}
      <div className="logo" style={styles.logo}>
        {/* <img className="MenuLogo" src={MenuLogo} alt="MenuLogo" /> */}
        <img
          className="AKo"
          src={defaultAkoImage}
          style={styles.AKo}
          alt="A-Ko"
        />
        <span style={styles.logoText}>A-Ko mentor</span>
      </div>
      {/* 나중에 움직이는 애니메이션 있는 버튼으로 바꾸기 */}
      {/* <img src={startchat} style={styles.startchat} /> */}
      <button
        style={{
          backgroundColor: "#A08D7D",
          color: "#333",
          border: "none",
          borderRadius: "20px",
          padding: "10px 20px",
          cursor: "pointer",
        }}
      >
        start chat ✏️
      </button>
      <div className="setting" style={styles.setting}>
        <img src={helpicon} alt="helpicon" style={styles.settingIcon} />

        <img src={settingsicon} alt="settingsicon" style={styles.settingIcon} />
        <img src={appsicon} alt="appsicon" style={styles.settingIcon} />
        <div className="userprofile" style={styles.userProfile}>
          USER
        </div>
      </div>
    </div>
  );
};

const styles = {
  header: {
    display: "flex",
    justifyContent: "space-between",
    alignItems: "center",
    padding: "10px 20px",
    backgroundColor: "rgba(244, 224, 200, 0.3)",
  },
  logo: {
    display: "flex",
    alignItems: "center",
  },
  logoText: {
    fontFamily: "SeoulHangang CM",
    fontSize: "20px",
    color: "#67574b",
    marginLeft: "10px",
  },
  setting: {
    display: "flex",
    alignItems: "center",
  },
  settingIcon: {
    marginRight: "10px",
  },
  userProfile: {
    width: "40px",
    height: "40px",
    borderRadius: "50%",
    backgroundColor: "#f4d48d",
    textAlign: "center",
    display: "flex",
    justifyContent: "center",
    alignItems: "center",
    fontSize: "14px",
    marginLeft: "10px",
  },
  AKo: {
    height: "40px",
    marginLeft: "10px", // Use camelCase for CSS property names
  },
  startchat: {
    height: "35px",
    width: "133px",
  },
};

export default HeaderComponent;
