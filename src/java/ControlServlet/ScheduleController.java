/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControlServlet;

import DAO.ScheduleDaos;
import DTO.Schedule;
import DTO.ScheduleDetails;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sun.util.calendar.BaseCalendar;

/**
 *
 * @author megap
 */
@WebServlet(name = "ScheduleController", urlPatterns = {"/ScheduleController"})
public class ScheduleController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");
        ScheduleDaos daos = new ScheduleDaos();
        ArrayList<String> timeList = new ArrayList<String>();//một mảng các mốc thời gian bệnh viện làm việc trong ngày
        timeList.add("7h00 a.m - 8h30 a.m");
        timeList.add("8h45 a.m - 10h15 a.m");
        timeList.add("10h30 a.m - 12h00 p.m");
        timeList.add("12h30 p.m - 14h00 p.m");
        timeList.add("14h15 p.m - 15h45 p.m");
        timeList.add("16h00 p.m - 17h30 p.m");
        timeList.add("18h00 p.m - 19h30 p.m");
        
        ArrayList<String> doctorList = new ArrayList<String>();
        doctorList.add("Paul Osborn");
        doctorList.add("Peter Wei");
        doctorList.add("Wei Liang");
        doctorList.add("Shie Jie");
        
        ArrayList<String> diseaseList = new ArrayList<String>();
        diseaseList.add("Cancer");
        diseaseList.add("Skin Allergy");
        
        Date curDate = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String minDate = dateFormat.format(curDate);
        
        if(action.equals("viewScheduleList") || action.equals("deleteSchedule")){
            String fromDate = request.getParameter("fromDate");
            String toDate = request.getParameter("toDate");
            String tempoID = request.getParameter("scheduleID");
            int scheduleID = 0;
            if(tempoID != null)
                scheduleID = Integer.decode(tempoID);
            
            if(fromDate == null)
                fromDate="";
            
            if(toDate == null)
                toDate = "";
            
            if(action.equals("deleteSchedule")){
                if(!daos.deleteSchedule(scheduleID))
                    response.sendRedirect("error.jsp");
            }
            
            if(toDate.isEmpty() && fromDate.isEmpty()) {
                request.setAttribute("scheduleList", daos.getAllSchedule());
            }else if(toDate.isEmpty()){
                request.setAttribute("scheduleList", daos.getScheduleByTime(fromDate, ""));
            }else {
                request.setAttribute("scheduleList", daos.getScheduleByTime(fromDate, toDate));
            }  
            request.setAttribute("minDate", minDate);
            RequestDispatcher rd = request.getRequestDispatcher("scheduleList.jsp");
            rd.forward(request, response);
            
            
        }else if(action.equals("Add") || action.equals("Update")){
            String room = request.getParameter("scheduleRoom");
            String time = request.getParameter("scheduleTime");
            String doctor = request.getParameter("doctor");
            String content = request.getParameter("content");
            String inputPrice = request.getParameter("price");
            String date = request.getParameter("date");
            String pattern = "[0-9]+";
            double price = 0;
            boolean status;
            if(request.getParameter("scheduleStatus").equals("True"))
                status = true;
            else
                status = false;
            
            int scheduleID = 0;
            if(action.equals("Update"))
                scheduleID = Integer.decode(request.getParameter("scheduleID"));
            
            
            boolean formStatus = false; //Biến kiểm tra thông tin điền vào có lỗi hay không
            
            if(!inputPrice.matches(pattern)){
                formStatus = true;
                request.setAttribute("priceAlert", "The cost must be a numeric value");
            }
            else
                price = Double.valueOf(inputPrice);
            if(price <= 0 && formStatus == false){
                formStatus = true;
                request.setAttribute("priceAlert", "The cost must be greater than 0");
            }
            if(room.isEmpty()){
                formStatus = true;
                request.setAttribute("roomAlert", "Room number must not be empty");
            }
            if(time.isEmpty()){
                formStatus = true;
                request.setAttribute("timeAlert", "Time must not be empty");
            }
            if(doctor.isEmpty()){
                formStatus = true;
                request.setAttribute("doctorAlert", "Doctor's name must not be empty");
            }
            if(content.isEmpty()){
                formStatus = true;
                request.setAttribute("contentAlert", "The diseases must not be empty");
            }
            if(request.getParameter("scheduleStatus") == null){
                formStatus = true;
                request.setAttribute("statusAlert", "The room;s status cannot be empty");
            }
            if(date.isEmpty()){
                formStatus = true;
                request.setAttribute("dateAlert", "Date cannot be empty");
            }
            if(action.equals("Add") && !date.isEmpty()){
                if(daos.getScheduleByTimeAndDoctor(time, doctor, date)){
                    formStatus=true;
                    request.setAttribute("doctorAlert", "The doctor is not available at that time");
                }            
                if(daos.getScheduleByTimeAndRoom(time, room, date)){
                    formStatus = true;
                    request.setAttribute("roomAlert", "The room is not available at that time");
                }
            }
            
            ScheduleDetails details = new ScheduleDetails(scheduleID, doctor, content, price);
            Schedule input = new Schedule(scheduleID, room, time, status, date, details);
            
            if(formStatus){
                request.setAttribute("doctorList", doctorList);
                request.setAttribute("diseaseList", diseaseList);
                request.setAttribute("scheduleItem", input);
                request.setAttribute("timeList", timeList);
                if(action.equals("Add")){
                    request.setAttribute("action", "Add");
                    request.setAttribute("message", "Add a new schedule");
                } else {
                    request.setAttribute("action", "Update");
                    request.setAttribute("message", "Update a schedule");
                }
                
                RequestDispatcher rd = request.getRequestDispatcher("scheduleForm.jsp");
                request.setAttribute("minDate", minDate);
                rd.forward(request, response);
            } else{
                if(action.equals("Add")){ //nếu hành động là add một schedule mới
                    if(daos.addSchedule(input))
                        response.sendRedirect("landingPageAdmin.jsp");
                    else
                        response.sendRedirect("error.jsp");
                } else if (action.equals("Update")){
                    if(daos.updateSchedule(input)){
                        RequestDispatcher rd = request.getRequestDispatcher("ScheduleController?action=viewScheduleList");
                        request.setAttribute("minDate", minDate);
                        rd.forward(request, response);
                    }
                    else
                        response.sendRedirect("error.jsp");
                }
            }

            
            
        }else if(action.equals("addSchedule") || action.equals("updateSchedule")){
            Schedule input = new Schedule();

            if(action.equals("updateSchedule")){
                int scheduleID = Integer.decode(request.getParameter("scheduleID"));
                input = daos.getScheduleByID(scheduleID);
            }
            request.setAttribute("doctorList", doctorList);
            request.setAttribute("diseaseList", diseaseList);
            request.setAttribute("scheduleItem", input);
            request.setAttribute("timeList", timeList);
            request.setAttribute("minDate", minDate);
            if(action.equals("addSchedule")){
                request.setAttribute("action", "Add");
                request.setAttribute("message", "Add a new schedule");
            }
            else if(action.equals("updateSchedule")){
                request.setAttribute("action", "Update");
                request.setAttribute("message", "Update an existing schedule");
            }
            RequestDispatcher rd = request.getRequestDispatcher("scheduleForm.jsp");
            rd.forward(request, response);
            
            
        }
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}