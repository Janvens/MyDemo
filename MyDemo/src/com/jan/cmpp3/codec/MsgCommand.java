package com.jan.cmpp3.codec;
public interface MsgCommand {
	 int CMPP_CONNECT=0x00000001;//CMPP_CONNECT��������
	 int CMPP_CONNECT_RESP=0x80000001;//��������Ӧ��
	 int CMPP_TERMINATE=0x00000002;//	��ֹ����
	 int CMPP_TERMINATE_RESP=0x80000002;	//��ֹ����Ӧ��
	 int CMPP_SUBMIT=0x00000004;	//�ύ����
	 int CMPP_SUBMIT_RESP=0x80000004;	//�ύ����Ӧ��
	 int CMPP_DELIVER=0x00000005;	//�����·�
	 int CMPP_DELIVER_RESP=0x80000005;	//�·�����Ӧ��
	 int CMPP_QUERY=0x00000006;	//���Ͷ���״̬��ѯ
	 int CMPP_QUERY_RESP=0x80000006;	//���Ͷ���״̬��ѯӦ��
	 int CMPP_CANCEL=0x00000007;	//ɾ������
	 int CMPP_CANCEL_RESP=0x80000007;	//ɾ������Ӧ��
	 int CMPP_ACTIVE_TEST=0x00000008;	//�������
	 int CMPP_ACTIVE_TEST_RESP=0x80000008;	//�������Ӧ��
}