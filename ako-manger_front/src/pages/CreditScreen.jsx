import React, { useState } from "react";

import HeaderComponent from "./components/HeaderComponent";
import PieChartComponent from "./components/PieChartComponent";
import Curriculum from "./components/curriculum";

import "../css/CreditScreen.css";
import {
  PieChartOutlined,
  ApartmentOutlined,
  CarryOutOutlined,
  MenuUnfoldOutlined,
  MenuFoldOutlined,
  QuestionCircleOutlined,
} from "@ant-design/icons";
import { TinyColor } from "@ctrl/tinycolor";
import { Layout, Menu, Button, FloatButton, Popover } from "antd";

const { Sider } = Layout;

//학점 조회 차트 더미 데이터
const chartData = [
  {
    title: "졸업까지",
    value: 55,
    totalValue: 150,
    color: "#F1BB79",
  },
  {
    title: "전공",
    value: 30,
    totalValue: 80,
    color: "#867060",
  },
  {
    title: "공통 교양",
    value: 20,
    totalValue: 50,
    color: "rgba(230, 170, 15, 0.56)",
  },
  {
    title: "일반 교양",
    value: 5,
    totalValue: 20,
    color: "#B3C278",
  },
];

// 필수 과목 이수 체계도 더미 데이터
const curriculumDummyData = [
  {
    grade: 1,
    semester: 1,
    sbj: "계산적 사고법",
    prev: null,
    major: true,
  },
  {
    grade: 1,
    semester: 2,
    sbj: "어드벤처 디자인",
    prev: null,
    major: true,
  },
  {
    grade: 1,
    semester: 2,
    sbj: "이산수학",
    prev: null,
    major: false,
  },
  {
    grade: 2,
    semester: 1,
    sbj: "자료구조와 실습",
    prev: "프로그래밍 기초",
    major: true,
  },
  {
    grade: 2,
    semester: 1,
    sbj: "컴퓨터 구성",
    prev: "이산수학",
    major: false,
  },
  {
    grade: 2,
    semester: 2,
    sbj: "시스템소프트웨어와 실습",
    prev: "자료구조와 실습",
    major: true,
  },
  {
    grade: 3,
    semester: 1,
    sbj: "공개SW프로젝트",
    prev: "자료구조와 실습",
    major: true,
  },
];

const CreditScreen = () => {
  // 왼쪽 네비게이션 관련 함수-1
  const handleMenuClick = (e) => {
    const sectionId = `part-${e.key}`;
    const section = document.getElementById(sectionId);
    if (section) {
      section.scrollIntoView({ behavior: "smooth" });
    }
  };
  // 왼쪽 네비게이션 관련 함수-2
  const [collapsed, setCollapsed] = useState(false);

  return (
    <div>
      <FloatButton
        description="✏️ 다음 학기 시간표 추천해 줄게요"
        shape="square"
        className="custom-float-button"
        //onClick => 클릭시 시간표 보여주기(time table 라이브러리 사용)
        style={{
          right: 50,
          fontSize: 100,
          width: 250,
          height: 40,
          marginRight: 10,
        }}
      />

      <HeaderComponent />
      <div className="CreditScreen-container">
        <Sider trigger={null} collapsible collapsed={collapsed} theme="light">
          <Menu
            mode="inline"
            defaultSelectedKeys={["1"]}
            theme="light"
            className="custom-menu"
            onClick={handleMenuClick}
          >
            <Menu.Item key="1" icon={<PieChartOutlined />}>
              이수 학점 조회
            </Menu.Item>
            <Menu.Item key="2" icon={<ApartmentOutlined />}>
              교과목 이수 체계도
            </Menu.Item>
          </Menu>
        </Sider>
        <Button
          type="text"
          icon={collapsed ? <MenuUnfoldOutlined /> : <MenuFoldOutlined />}
          onClick={() => setCollapsed(!collapsed)}
          style={{
            fontSize: "16px",
            width: 64,
            height: 64,
          }}
        />
        <div className="item-container">
          <h2
            id="part-1"
            className="curriculum-title"
            style={{
              marginBottom: "50px",
              display: "flex",
              justifyContent: "center",
              alignItems: "center",
              flexDirection: "column",
            }}
          >
            이수 학점 조회
          </h2>
          <div className="chart-wrap">
            {chartData.map((dataItem, index) => (
              <div key={index} className="chart-section">
                <p className="chart-title">{dataItem.title}</p>
                <div className="chart-content">
                  <PieChartComponent data={dataItem} />
                  <p className="chart-credits">총{dataItem.totalValue}학점</p>
                </div>
              </div>
            ))}
          </div>
          <div id="part-2" className="curriculum-wrap">
            <h2
              className="curriculum-title"
              style={{
                marginBottom: "50px",
                display: "flex",
                justifyContent: "center",
                alignItems: "center",
                flexDirection: "column",
                marginTop: "25px",
              }}
            >
              (전공명) 필수 과목 이수체계도
            </h2>
            <div className="curriculum-section">
              <Curriculum data={curriculumDummyData}></Curriculum>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default CreditScreen;
