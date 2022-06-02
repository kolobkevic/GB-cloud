package org.kolobkevic.GB_cloud.ui.netty.common;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.kolobkevic.GB_cloud.ui.ServerPanelController;
import org.kolobkevic.GB_cloud.ui.netty.common.dto.*;
import org.kolobkevic.GB_cloud.ui.netty.client.ClientService;
import org.kolobkevic.GB_cloud.ui.netty.common.dto.BasicResponse;

import java.io.File;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ClientHandler extends ChannelInboundHandlerAdapter {
    private Date date;
    private final static DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss.SSS");
    private final ClientService clientService = new ClientService();

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        date = new Date();
        super.channelActive(ctx);
        System.out.println(dateFormat.format(date) + ": клиентский канал активирован. Адрес сервера: " + ctx.channel().remoteAddress());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        date = new Date();

        BasicResponse response = (BasicResponse) msg;

        if (response instanceof AuthResponse) {
            String responseText = ((AuthResponse) response).getResult();
            System.out.println(dateFormat.format(date) + ": получен AuthResponse");
            if ("login ok".equals(responseText)) {
                ctx.writeAndFlush(new GetFileListRequest());
                System.out.println(dateFormat.format(date) + " на адрес " + ctx.channel().remoteAddress() + " отправлен GetFileListRequest");
                clientService.loginOk();
                return;
            }
            if ("login bad".equals(responseText)) {
                System.out.println(dateFormat.format(date) + ": login bad");
            }
            return;
        }

        if (response instanceof GetFileListResponse) {
            GetFileListResponse getFileListResponse = (GetFileListResponse) response;
            ServerPanelController.CURRENT_USER_SERVER_DIR = Paths.get(getFileListResponse.getClientStringDir());
            System.out.println(dateFormat.format(date) + ": получен GetFileListResponse. CURRENT_USER_SERVER_DIR = " + getFileListResponse.getClientStringDir());
            List<File> serverItemList = ((GetFileListResponse) response).getItemList();
            clientService.putServerFileList(ServerPanelController.CURRENT_USER_SERVER_DIR, serverItemList);
            return;
        }

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println(dateFormat.format(date) + " - на клиенте в момент обработки ClientHandler поймали исключение");
        cause.printStackTrace();
        ctx.close();
    }

}
