# ������� 1: ������� ����� ������� ���������� �������

���������� ��������:
���������:
@TrackTime  - ��� ����������� ������������ ������� ���������� �������
@TrackAsyncTime - - ��� ������������ ������������ ������� ���������� �������


�������:

      TrackTimeAspect - ����������� ������� ���������� ������, ����������� ���������� @TrackTime � ������ ���������� � ��.

            ������ ���������� ������� ������  GET /api/animal/animalTypes  ������� ����� ��������

      TrackAsyncTimeAspect - ����������� ����������� ������� ���������� ������, ����������� ���������� @TrackAsyncTime � ������ ���������� � ��.

            ������ ���������� ������� ������  GET /api/animal/animals ������� ��������

      CheckAccessAspect - �������� ������� � ��������� ������. 

            ������ ���������� ������� ������  PATCH  /api/animal/animals/{id} ��������� ���������

REST API

       REST API ���������� � �������������� �������� API First  �� ������ ������������ Open API � ������� ��������� ���� �� ������ ������������.

       ��� ��������� ������� ������ API � ��������� ������ ����� ���������� �� ������:   http://localhost:8081/swagger-ui/index.html#/ 

       Rest ������ GET /api/admin/trackTimeMetrics ������������ ��� ������� ���������� ���������� �������   



���� � �������

      ��� ����� � ������� ����� ������� POST /api/login  ���� � ������� � ��������
      {
        "username": "ADMIN",
               "password": "ADMIN"
             }

���

     {
              "username": "developer",
             "password": "developer"
          }

      ��� ������������ � ������� ������������ ��� ������������ ������������� ��� ��� ���������� �������� � �������.

���� ������

      � ���������� ������������ �������� �� �� VK Cloud.  ��������� ������������ � ����� property. 

����������������

      �������� ��� �������������� � ������������ � ������������ JavaDoc � ������������ ���������� ����� ��������� ������������ �� ����������.

      Rest ������� ��������� � ������������ Open API � �� ��� ����� ������������ ���������� ����� ��������� ������������.