import React from "react";

import LeftBubble from "./components/LeftBubble";
import RightBubble from "./components/Rightbibble";
import HeaderComponent from "./components/HeaderComponent";

import "../css/ChattingScreen.css";
import defaultAkoImage from "../Images/기본형아코얼굴_(designed by 박세리,원혜림).png";
import listeningAkoImage from "../Images/듣는아코_(designed by 박세리).png";
import phoneAkoImage from "../Images/핸드폰쥔아코_(designed by 박세리).png";
import searchlogo from "../Images/search.png";

const ChattingScreen = () => {
  return (
    <div className="wrap">
      <HeaderComponent />
      <div className="chatting-container">
        <div className="chatting-log">
          {/* 채팅 기록도 컴포넌트화 하기 */}
          <div className="log">채팅 기록 1</div>
          <div className="log">채팅 기록 2</div>
        </div>
        <div className="chatting-screen">
          <div className="chatwrapper">
            <div className="chat">
              <LeftBubble />
              <RightBubble />
              <LeftBubble />
              <RightBubble />
            </div>
            <div className="keywordcontainer">
              {/* 샵 키워드 컴포넌트로 분리*/}
            </div>
            <div className="inputcontainer">
              <img src={searchlogo} alt="searchlogo" />
              <input type="text" placeholder="Search"></input>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default ChattingScreen;
