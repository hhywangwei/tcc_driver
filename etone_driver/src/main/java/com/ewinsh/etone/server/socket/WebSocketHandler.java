package com.ewinsh.etone.server.socket;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.alibaba.fastjson.JSONObject;

public class WebSocketHandler extends SimpleChannelInboundHandler<String> {
	private static final Logger logger = LoggerFactory.getLogger(WebSocketHandler.class);
	private static final String ROOT_ELEMENT = "root";
	private static final String FULL_XML ="<?xml version=\"1.0\"?>\n<root>%s</root>";
	private static final String[] NOT_CONVERTS = {"head"};
	
	private final Channel _webSocketChannel;
	private final SAXParserFactory spf;

	public WebSocketHandler(Channel webSocketChannel) {
		_webSocketChannel = webSocketChannel;
		spf = SAXParserFactory.newInstance();
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, String msg)throws Exception {
		String json = convertToJson(msg);
		
		if(json == null){
			return ;
		}
		
		TextWebSocketFrame frame = new TextWebSocketFrame(json);
		_webSocketChannel.writeAndFlush(frame);
	}
	
	private String convertToJson(String msg){
		String xml = String.format(FULL_XML, msg);
		try{
			Map<String,String> map = parseXml(xml);
			JSONObject json = new JSONObject();
			for(Map.Entry<String, String> entry: map.entrySet()){
				if(isConvert(entry.getKey())){
					json.put(uncapitalize(entry.getKey()), entry.getValue());	
				}
			}
			return json.toJSONString();
		}catch(SAXException e){
			//none instance
			logger.error("Parse receve \"{}\" is error,error is {}", xml, e.getMessage());
			return null;
		}
	}
	
	private boolean isConvert(String key){
		boolean c = true;
		for(String s : NOT_CONVERTS){
			if(StringUtils.equals(s, key)){
				c = false;
				break;
			}
		}
		return c;
	}
	
	private String uncapitalize(String key){
		return StringUtils.uncapitalize(key);
	}
	
	private Map<String,String> parseXml(String xml)throws SAXException{
		Map<String, String> content = new HashMap<String, String>();
        try {
            SAXParser sp = spf.newSAXParser();
//            logger.debug("message xml is \"{}\"", xml);
            StringReader reader = new StringReader(xml);
            sp.parse(new InputSource(reader), new XmlHandler(content));
        } catch (ParserConfigurationException e) {
            //none instance
            logger.error("ParserConfigurationException is exception:{}", e);
        } catch (IOException e) {
            //none instance
            logger.error("IOException is exception:{}", e);
        }

        return content;
	}

	private static class XmlHandler extends DefaultHandler {

        private final Map<String, String> _content;
        private StringBuilder _value = new StringBuilder();

        public XmlHandler(Map<String, String> content) {
            _content = content;
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            int len = _value.length();
            if (len != 0) {
                _value.delete(0, len);
            }
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            _value.append(new String(ch, start, length));
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            if (StringUtils.equals(ROOT_ELEMENT, qName)) {
                return;
            }

            String v = _value.toString();
            _content.put(qName, v);
        }
    }
}
